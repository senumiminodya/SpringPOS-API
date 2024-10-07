package lk.ijse.springposbackendapi.service;

import lk.ijse.springposbackendapi.dto.CustomerStatus;
import lk.ijse.springposbackendapi.dto.ItemStatus;
import lk.ijse.springposbackendapi.dto.impl.CustomerDTO;
import lk.ijse.springposbackendapi.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
    ItemStatus getItem(String itemId);
    void deleteItem(String itemId);
    void updateItem(String itemId, ItemDTO itemDTO);
}
