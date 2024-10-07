package lk.ijse.springposbackendapi.controller;

import lk.ijse.springposbackendapi.customStatusCode.SelectedCustomerItemAndOrderStatus;
import lk.ijse.springposbackendapi.dto.CustomerStatus;
import lk.ijse.springposbackendapi.dto.ItemStatus;
import lk.ijse.springposbackendapi.dto.impl.CustomerDTO;
import lk.ijse.springposbackendapi.dto.impl.ItemDTO;
import lk.ijse.springposbackendapi.exception.CustomerNotFoundException;
import lk.ijse.springposbackendapi.exception.DataPersistException;
import lk.ijse.springposbackendapi.exception.ItemNotFoundException;
import lk.ijse.springposbackendapi.service.CustomerService;
import lk.ijse.springposbackendapi.service.ItemService;
import lk.ijse.springposbackendapi.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.saveItem(itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{itemId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getSelectedItem(@PathVariable ("itemId") String itemId) {
        if (!RegexProcess.itemIdMatcher(itemId)) {
            return new SelectedCustomerItemAndOrderStatus(1,"Item ID is not valid");
        }
        return itemService.getItem(itemId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") String itemId) {
        try {
            if (!RegexProcess.itemIdMatcher(itemId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable ("itemId") String itemId, @RequestBody ItemDTO updatedItemDTO) {
        itemService.updateItem(itemId, updatedItemDTO);
        //validations
        try {
            if (!RegexProcess.itemIdMatcher(itemId) || updatedItemDTO==null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemId,updatedItemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
