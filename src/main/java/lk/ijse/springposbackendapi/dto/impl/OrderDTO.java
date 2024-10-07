package lk.ijse.springposbackendapi.dto.impl;

import lk.ijse.springposbackendapi.dto.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO implements OrderStatus {
    private String orderId;
    private LocalDate orderDate;
    private Double total;
    private String customerId;
    private String customerName;
    private List<ItemDTO> items;
    private String status;
}
