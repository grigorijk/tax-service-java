package com.labs.game.taxservice;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TaxServiceTest {

    private TaxService taxService;

    @Before
    public void setUp() {
        taxService = new TaxService();
    }

    @Test
    public void calculateTax_returns10_forShipping() {
        assertTax("shipping", 10);
    }

    private void assertTax(String product, int expectedTax) {
        final Tax tax = taxService.calculateTax(product);
        assertThat(tax.getProduct()).isEqualTo(product);
        assertThat(tax.getTax()).isEqualTo(expectedTax);
    }

    @Test
    public void calculateTax_returns0_forEEEProduct() {
        assertTax("EEE", 0);
    }

    @Test
    public void calculateTax_returns10_forXXXProduct() {
        assertTax("AAA", 10);
    }

    @Test
    public void calculateTax_throwsError_forWrongProduct() {
        assertThatThrownBy(() -> taxService.calculateTax("ZZZZ"))
                .isInstanceOf(UnknownProductCodeException.class);
    }

}
