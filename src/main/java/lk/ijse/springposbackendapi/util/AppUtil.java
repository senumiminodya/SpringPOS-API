package lk.ijse.springposbackendapi.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCustomerId() {
        return "CUSTOMER-"+ UUID.randomUUID();
    }
}
