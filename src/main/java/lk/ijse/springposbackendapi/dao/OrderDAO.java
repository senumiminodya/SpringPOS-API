package lk.ijse.springposbackendapi.dao;

import lk.ijse.springposbackendapi.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, String> {

}
