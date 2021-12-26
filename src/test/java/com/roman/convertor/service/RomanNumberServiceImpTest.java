package com.roman.convertor.service;

import com.roman.convertor.models.RomanNumeral;
import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class RomanNumberServiceImpTest {

    RomanNumberServiceImp service = new RomanNumberServiceImp();

    /***
     * passed correct input and expected to receive valid output for a given input.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "1,I",
            "5,V",
            "555,DLV",
            "100,C",
            "101,CI",
            "400,CD",
            "500,D",
            "7,VII",
            "10,X",
            "12,XII",
            "50,L",
            "55,LV",
            "3000,MMM",
            "3004,MMMIV"
    })
    @DisplayName("happy test case")
    public void testCreateRomanNumeral(String s) {
        String[] input = s.split(",");
        RomanNumeral romanNumeral = service.convert(Integer.parseInt(input[0]));
        assertEquals(input[1], romanNumeral.getOutput());
    }

    /***
     * passed correct input and expected to receive valid output for a given input.
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    @DisplayName("happy test case - for all possible numbers")
    void positiveTestCalculateRomanNumbers(String input, String expected) {
        Map<Integer, RomanNumeral> cache = service.calculateRomanNumbers();
        RomanNumeral romanNumeral = cache.get(Integer.parseInt(input));
        assertEquals(expected, romanNumeral.getOutput());
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0,
            -2,
            -133,
            4000,
    })
    @DisplayName("negative test case -  passed out of range numbers, output supposed to be null")
    public void testInvalidRomanNumeral(int numeral) {
        RomanNumeral romanNumeral = service.convert(numeral);
        assertEquals(null, romanNumeral.getOutput());
    }
}