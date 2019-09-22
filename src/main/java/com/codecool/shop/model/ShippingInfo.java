package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingInfo {

    private String name;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;

    public ShippingInfo(String name, String email, String phoneNumber, String billingAddress, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public String toString() {
        return String.format("%1$s={" +
                        "name: %2$s, " +
                        "email: %3$s, " +
                        "phoneNumber: %4$s, " +
                        "billingAddress: %5$s, " +
                        "shippingAddress: %6$s}",
                getClass().getSimpleName(),
                name,
                email,
                phoneNumber,
                billingAddress,
                shippingAddress
        );
    }
}
