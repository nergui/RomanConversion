package com.roman.converter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RomanNumeral {
    private Integer numeral;
    private String romanNumeral;
    public RomanNumeral(Integer numeral) {
        this.numeral = numeral;
    }
}
