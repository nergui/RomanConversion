package com.roman.convertor.service;

import com.roman.convertor.models.RomanNumeral;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class RomanNumberServiceImp implements RomanNumberService {
    private final static NavigableMap<Integer, String> romanNumerals = new TreeMap<>();
    private final int MIN_ROMAN_NUMBER = 1;
    private final int MAX_ROMAN_NUMBER = 3999;
    private Map<Integer, RomanNumeral> romanNumberCache = null;

    static {
        romanNumerals.put(1, "I");
        romanNumerals.put(4, "IV");
        romanNumerals.put(5, "V");
        romanNumerals.put(9, "IX");
        romanNumerals.put(10, "X");
        romanNumerals.put(40, "XL");
        romanNumerals.put(50, "L");
        romanNumerals.put(90, "XC");
        romanNumerals.put(100, "C");
        romanNumerals.put(400, "CD");
        romanNumerals.put(500, "D");
        romanNumerals.put(900, "CM");
        romanNumerals.put(1000, "M");
    }

    public RomanNumberServiceImp() {
        romanNumberCache = calculateRomanNumbers();
    }

    /**
     * give int number, calculate roman numeral and return it.
     *
     * @param number
     * @return {input: number, output: romanNumber}
     */
    @Override
    public RomanNumeral convert(int number) {
        if (number < MIN_ROMAN_NUMBER || number > MAX_ROMAN_NUMBER) {
            return new RomanNumeral();
        }
        RomanNumeral romanNumeral = new RomanNumeral();
        romanNumeral.setInput(String.valueOf(number));
        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            int floored = romanNumerals.floorKey(number);
            String numeral = romanNumerals.get(floored);
            sb.append(numeral);
            number -= floored;
        }
        romanNumeral.setOutput(sb.toString());
        return romanNumeral;
    }

    /**
     * since we have a fixed number roman numbers(1-3999), we can pre-calculate and store it in Hashmap as cache.
     * so, subsequent request will pick it up from cache.
     *
     * @return [{1,I}, {2, II} ... ]
     */
    public Map<Integer, RomanNumeral> calculateRomanNumbers() {
        Map<Integer, RomanNumeral> mutableMap = new HashMap<>();
        for (int i = MIN_ROMAN_NUMBER; i <= MAX_ROMAN_NUMBER; i++) {
            mutableMap.put(i, this.convert(i));
        }
        return Collections.unmodifiableMap(mutableMap);
    }

    /***
     * given int number, return RomanNumeral with valid roman numeral
     *
     * @param number
     * @return
     */
    @Override
    public RomanNumeral getRomanNumber(int number) {
        return romanNumberCache.get(number);
    }
}
