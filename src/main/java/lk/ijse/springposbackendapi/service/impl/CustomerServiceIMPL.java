package lk.ijse.springposbackendapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springposbackendapi.customStatusCode.SelectedCustomerItemAndOrderStatus;
import lk.ijse.springposbackendapi.dao.CustomerDAO;
import lk.ijse.springposbackendapi.dto.CustomerStatus;
import lk.ijse.springposbackendapi.dto.impl.CustomerDTO;
import lk.ijse.springposbackendapi.entity.impl.CustomerEntity;
import lk.ijse.springposbackendapi.exception.CustomerNotFoundException;
import lk.ijse.springposbackendapi.exception.DataPersistException;
import lk.ijse.springposbackendapi.service.CustomerService;
import lk.ijse.springposbackendapi.util.AppUtil;
import lk.ijse.springposbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping customerMapping;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(AppUtil.generateCustomerId());
        CustomerEntity savedCustomer =
                customerDAO.save(customerMapping.toCustomerEntity(customerDTO));
        if(savedCustomer == null){
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMapping.asCustomerDTOList(customerDAO.findAll());
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        if(customerDAO.existsById(customerId)){
            var selectedCustomer = customerDAO.getReferenceById(customerId);
            return customerMapping.toCustomerDTO(selectedCustomer);
        }else {
            return new SelectedCustomerItemAndOrderStatus(2,"Selected customer not found");
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> existedCustomer = customerDAO.findById(customerId);
        if (!existedCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id "+customerId+" not found!");
        }else {
            customerDAO.deleteById(customerId);
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> findCustomer = customerDAO.findById(customerId);
        if (!findCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer not found!");
        } else {
            findCustomer.get().setNic(customerDTO.getNic());
            findCustomer.get().setName(customerDTO.getName());
            findCustomer.get().setPhoneNo(customerDTO.getPhoneNo());
        }
    }
}
