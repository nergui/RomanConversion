package com.roman.converter.controller;

import com.roman.converter.models.RomanNumeral;
import com.roman.converter.service.RomanNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
     * <p>
     * For example:
     * /romannumeral?query=3
     *      {
     *          numeral: 3
     *          romanNumeral: III
     *      }
     *
     * @param query input to convert to roman numeral
     * @return json body that contains original number and roman numeral conversion.
     */
    @RequestMapping("/romannumeral")
    public RomanNumeral romanNumeral(@RequestParam(value = "query", defaultValue = "1") Integer query) {
        if (query < 1 || query > 3999) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested roman numeral is not between 1 and 3999");
        }
        log.info("User requested query " + query);

        //return romanNumberService.convert(query);
        return romanNumberService.getRomanNumber(query);
    }
}
