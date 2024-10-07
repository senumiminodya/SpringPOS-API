package lk.ijse.springposbackendapi.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String code;
    private String name;
    private Double price;
    private int qty;
    @ManyToMany(mappedBy = "items")
    private List<OrderEntity> orders;
}
