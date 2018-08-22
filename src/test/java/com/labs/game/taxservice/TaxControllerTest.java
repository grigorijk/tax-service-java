package com.labs.game.taxservice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxController.class)
public class TaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxService taxService;

    @Test
    public void getTaxByProduct_ShouldReturnTax() throws Exception {
        given(taxService.calculateTax(anyString())).willReturn(new Tax("AAA", 100));
        mockMvc.perform(MockMvcRequestBuilders.get("/taxes/AAA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("product").value("AAA"))
                .andExpect(jsonPath("tax").value(100));
    }

    @Test
    public void getTaxByProduct_ShouldReturn400_WhenWrongCode() throws Exception {
        given(taxService.calculateTax(anyString())).willThrow(new UnknownProductCodeException());
        mockMvc.perform(MockMvcRequestBuilders.get("/taxes/AAAA"))
                .andExpect(status().is4xxClientError());

    }
}
