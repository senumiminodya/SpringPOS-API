package lk.ijse.springposbackendapi.dto.impl;

import lk.ijse.springposbackendapi.dto.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements CustomerStatus {
    private Long id;
    private String nic;
    private String name;
    private String phoneNo;
}
