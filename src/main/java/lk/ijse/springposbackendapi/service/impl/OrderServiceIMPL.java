package lk.ijse.springposbackendapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springposbackendapi.dao.CustomerDAO;
import lk.ijse.springposbackendapi.dao.ItemDAO;
import lk.ijse.springposbackendapi.dao.OrderDAO;
import lk.ijse.springposbackendapi.dto.impl.ItemDTO;
import lk.ijse.springposbackendapi.dto.impl.OrderDTO;
import lk.ijse.springposbackendapi.entity.impl.CustomerEntity;
import lk.ijse.springposbackendapi.entity.impl.ItemEntity;
import lk.ijse.springposbackendapi.entity.impl.OrderEntity;
import lk.ijse.springposbackendapi.exception.OrderNotFoundException;
import lk.ijse.springposbackendapi.service.OrderService;
import lk.ijse.springposbackendapi.util.AppUtil;
import lk.ijse.springposbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapping;

    @Transactional
    @Override
    public void saveOrder(OrderDTO orderDTO) {
        orderDTO.setOrderId(AppUtil.generateOrderId());
        System.out.println("OrderDTO is:"+orderDTO);
        OrderEntity orderEntity = mapping.toOrderEntity(orderDTO);

        // Mapping CustomerDTO to CustomerEntity
        CustomerEntity customerEntity = customerDAO.findById(orderDTO.getCustomer().getId())
                .orElseGet(() -> mapping.toCustomerEntity(orderDTO.getCustomer()));
        // If the customer is new, save the customer first
        if (customerEntity.getId() == null) {
            customerDAO.save(customerEntity);
        }
        orderEntity.setCustomer(customerEntity);

        // Save or update the items if they are transient (not yet persisted)
        for (ItemEntity item : orderEntity.getItems()) {
            if (!itemDAO.existsById(item.getCode())) {
                itemDAO.save(item);  // Save item if not already present
            }
            int qtyInOrder = item.getQty();
            int qtyInInventory = itemDAO.getReferenceById(item.getCode()).getQty();
            int newQty = qtyInInventory - qtyInOrder;
            item.setQty(newQty);
            ItemDTO itemDTO = mapping.toItemDTO(item);
            itemDTO.setQty(newQty);
            itemDAO.save(item);
            itemDAO.flush();
        }
        // Save the order
        try {
            orderDAO.save(orderEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error saving order: " + e.getMessage());
        }
    }
    @Override
    public OrderDTO getOrderById(String orderId) {
        Optional<OrderEntity> orderEntity = orderDAO.findById(orderId);
        if (orderEntity.isPresent()) {
            OrderDTO orderDTO = mapping.toOrderDTO(orderEntity.get());
            orderDTO.setCustomer(mapping.toCustomerDTO(orderEntity.get().getCustomer()));
            return orderDTO;
        } else {
            throw new OrderNotFoundException("Order not found for ID: " + orderId);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orderEntities = orderDAO.findAll();
        return mapping.asOrderDTOList(orderEntities);
    }

    @Transactional
    @Override
    public void updateOrder(String orderId, OrderDTO orderDTO) {
        OrderEntity orderEntity = orderDAO.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));

        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setTotal(orderDTO.getTotal());

        // Update customer and items
        CustomerEntity customerEntity = mapping.toCustomerEntity(orderDTO.getCustomer());
        orderEntity.setCustomer(customerEntity);

        orderDAO.save(orderEntity);
    }

    @Transactional
    @Override
    public void deleteOrder(String orderId) {
        if (orderDAO.existsById(orderId)) {
            orderDAO.deleteById(orderId);
        } else {
            throw new OrderNotFoundException("Order not found for ID: " + orderId);
        }
    }

}
