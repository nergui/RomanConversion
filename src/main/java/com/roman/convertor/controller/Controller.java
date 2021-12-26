package com.roman.convertor.controller;

import com.roman.convertor.exception.BadRequestException;
import com.roman.convertor.models.RomanNumeral;
import com.roman.convertor.service.RomanNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    private RomanNumberService romanNumberService;

    @Autowired
    public Controller(RomanNumberService romanNumberService) {
        this.romanNumberService = romanNumberService;
    }

    /**
     * RomanNumeralController
     * Given an input number returns the corresponding roman numeral.
     * For example:
     * /romannumeral?query=3
     * {
     * input: "3"
     * output: "III"
     * }
     *
     * @param query input to convert to roman numeral
     * @return json body that contains original number and roman numeral conversion.
     */
    @RequestMapping("/romannumeral")
    public RomanNumeral romanNumeral(@RequestParam(value = "query", required = false) String query) {
            Integer input = Integer.parseInt(query);
            if (input < 1 || input > 3999) {
                throw new BadRequestException("Query input must be a valid number and between 1 and 3999");
            }
            log.info("User requested query " + query);
            return romanNumberService.getRomanNumber(input);
    }
}
