package lk.ijse.springposbackendapi.dao;

import lk.ijse.springposbackendapi.entity.impl.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<CustomerEntity, String> {
}
