package com.roman.converter.service;

import com.roman.converter.models.RomanNumeral;

/***
 *
 * this interface for roman number conversion maybe later we might come up with different implementation
 *
 */
public interface RomanNumberService {
    RomanNumeral convert(int number);
    RomanNumeral getRomanNumber(int number);
}
