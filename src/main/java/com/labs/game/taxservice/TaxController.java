package com.labs.game.taxservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@AllArgsConstructor
public class TaxController {

    @Autowired
    private TaxService taxService;

    @GetMapping("/taxes/{product}")
    public Tax getTax(@PathVariable String product) {
        return taxService.calculateTax(product);
    }


}
