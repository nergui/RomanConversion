package com.roman.convertor.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RomanNumeral {
    private String input;
    private String output;
    public RomanNumeral(String numeral) {
        this.input = numeral;
    }
}
