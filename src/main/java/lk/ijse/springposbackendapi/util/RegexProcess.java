package lk.ijse.springposbackendapi.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean customerIdMatcher(String customerId) {
        String regexForCustomerID = "^CUSTOMER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCustomerID);
        return regexPattern.matcher(customerId).matches();
    }
}
