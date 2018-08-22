package com.labs.game.taxservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Tax {
    private String product;
    private int tax;
}
