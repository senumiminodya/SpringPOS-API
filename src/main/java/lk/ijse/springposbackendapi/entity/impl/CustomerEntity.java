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
    private String id;  // Unique identifier for customer

    @Column(nullable = false, unique = true)
    private String nic;  // National ID or similar unique identifier

    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNo;  // Phone number

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}
