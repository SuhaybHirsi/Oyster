package com.tfl.billing;

import java.util.List;
import java.util.UUID;


public interface Database {
//    static Database getInstance() {
//    }

    List<CustomerInterface> getCustomers();

    boolean isRegisteredId(UUID cardId);
}
