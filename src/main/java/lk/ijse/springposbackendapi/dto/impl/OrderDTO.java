package lk.ijse.springposbackendapi.dto.impl;

import lk.ijse.springposbackendapi.dto.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO implements OrderStatus {
    private Long orderId;
    private LocalDate orderDate;
    private Double total;
    private Long customerId;
    private String customerName;
    private List<ItemDTO> items;
    private String status;
}
