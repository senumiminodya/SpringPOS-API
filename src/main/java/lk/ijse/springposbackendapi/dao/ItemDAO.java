package lk.ijse.springposbackendapi.dao;

import lk.ijse.springposbackendapi.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemEntity, String> {
}
