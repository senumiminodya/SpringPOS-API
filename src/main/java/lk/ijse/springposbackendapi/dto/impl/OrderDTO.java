package lk.ijse.springposbackendapi.dto.impl;

import lk.ijse.springposbackendapi.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements OrderStatus {
    private String orderId;
    private LocalDate orderDate;
    private Double total;
    private CustomerDTO customer;
    private List<ItemDTO> items;
}
