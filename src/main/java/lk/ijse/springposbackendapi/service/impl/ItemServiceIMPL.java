package lk.ijse.springposbackendapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springposbackendapi.customStatusCode.SelectedCustomerItemAndOrderStatus;
import lk.ijse.springposbackendapi.dao.ItemDAO;
import lk.ijse.springposbackendapi.dto.ItemStatus;
import lk.ijse.springposbackendapi.dto.impl.ItemDTO;
import lk.ijse.springposbackendapi.entity.impl.ItemEntity;
import lk.ijse.springposbackendapi.exception.CustomerNotFoundException;
import lk.ijse.springposbackendapi.exception.DataPersistException;
import lk.ijse.springposbackendapi.exception.ItemNotFoundException;
import lk.ijse.springposbackendapi.service.ItemService;
import lk.ijse.springposbackendapi.util.AppUtil;
import lk.ijse.springposbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private Mapping itemMapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setCode(AppUtil.generateItemId());
        ItemEntity savedItem = itemDAO.save(itemMapping.toItemEntity(itemDTO));
        if(savedItem == null){
            throw new DataPersistException("Item not saved");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemMapping.asItemDTOList(itemDAO.findAll());
    }

    @Override
    public ItemStatus getItem(String itemId) {
        if(itemDAO.existsById(itemId)){
            var selectedItem = itemDAO.getReferenceById(itemId);
            return itemMapping.toItemDTO(selectedItem);
        }else {
            return new SelectedCustomerItemAndOrderStatus(2,"Selected item not found");
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> existedItem = itemDAO.findById(itemId);
        if (!existedItem.isPresent()) {
            throw new ItemNotFoundException("Item with id "+itemId+" not found!");
        }else {
            itemDAO.deleteById(itemId);
        }
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        Optional<ItemEntity> findItem = itemDAO.findById(itemId);
        if (!findItem.isPresent()){
            throw new CustomerNotFoundException("Item not found!");
        } else {
            findItem.get().setName(itemDTO.getName());
            findItem.get().setPrice(itemDTO.getPrice());
            findItem.get().setQty(itemDTO.getQty());
        }
    }
}
