package com.qrscnner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentData {
    private double amount;
    private String currency;
    private String recipient;
    private String reference;
    private String vpa;
}
