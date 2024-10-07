package lk.ijse.springposbackendapi.dto.impl;

import lk.ijse.springposbackendapi.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements ItemStatus {
    private String code;
    private String name;
    private Double price;
    private int qty;
}
