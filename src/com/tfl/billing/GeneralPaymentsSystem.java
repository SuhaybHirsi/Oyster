package com.tfl.billing;



import java.math.BigDecimal;
import java.util.List;

public interface GeneralPaymentsSystem {
//    static com.tfl.external.PaymentsSystem getInstance() {
//        return PaymentSystemTemp.instance;
//    }

    void charge(CustomerInterface customer, List<Journey> journeys, BigDecimal totalBill);
}
