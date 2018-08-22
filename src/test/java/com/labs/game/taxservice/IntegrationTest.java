package com.labs.game.taxservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProduct_returnsOK() {
        // arrange
        // act
        final ResponseEntity<Tax> products = restTemplate.getForEntity("/taxes/AAA", Tax.class);

        // assert
        assertThat(products.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(products.getBody()).isNotNull();
        assertThat(products.getBody().getProduct()).isEqualTo("AAA");
        assertThat(products.getBody().getTax()).isEqualTo(10);


    }
}
