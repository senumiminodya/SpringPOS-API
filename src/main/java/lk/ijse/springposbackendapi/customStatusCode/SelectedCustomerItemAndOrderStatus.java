package lk.ijse.springposbackendapi.customStatusCode;

import lk.ijse.springposbackendapi.dto.CustomerStatus;
import lk.ijse.springposbackendapi.dto.ItemStatus;
import lk.ijse.springposbackendapi.dto.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCustomerItemAndOrderStatus implements CustomerStatus, ItemStatus, OrderStatus {
    private int status;
    private String statusMessage;
}
