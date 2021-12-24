package com.roman.converter.service;

import com.roman.converter.models.RomanNumeral;

/***
 * In order to support different implications for roman conversion,
 * I have created an interface and exposed 2 main methods that might be useful.
 *
 */
public interface RomanNumberService {
    RomanNumeral convert(int number);
    RomanNumeral getRomanNumber(int number);
}
