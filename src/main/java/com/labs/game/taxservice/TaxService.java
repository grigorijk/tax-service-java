package com.labs.game.taxservice;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class TaxService {

    private static Pattern PRODUCT_PATTERN = Pattern.compile("[A-Z]{3}");

    Tax calculateTax(String productName) {
        final int tax;
        if ("shipping".equals(productName))
            tax = 10;
        else if ("EEE".equals(productName))
            tax = 0;
        else if (PRODUCT_PATTERN.matcher(productName).matches())
            tax = 10;
        else
            throw new UnknownProductCodeException();

        return new Tax(productName, tax);
    }
}
