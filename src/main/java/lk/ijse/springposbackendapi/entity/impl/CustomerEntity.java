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
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String nic;
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNo;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}
