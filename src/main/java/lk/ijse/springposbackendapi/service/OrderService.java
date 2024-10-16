package lk.ijse.springposbackendapi.service;

import lk.ijse.springposbackendapi.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(String orderId);

    List<OrderDTO> getAllOrders();

    void updateOrder(String orderId, OrderDTO orderDTO);

    void deleteOrder(String orderId);
}
