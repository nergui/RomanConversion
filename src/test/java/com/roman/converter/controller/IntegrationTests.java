package com.roman.converter.controller;

import com.roman.converter.ConverterApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConverterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

    private static final String ENDPOINT = "/romannumeral?query=";
    @LocalServerPort
    private int portNumber;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private String createURLPort(String uri) {
        return String.format("http://localhost:%d%s", portNumber, uri) ;
    }

    @Test
    public void positiveTestWithCorrectNumber() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "2021"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"input\":\"2021\",\"output\":\"MMXXI\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void negativeTestWithEmptyRequest(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }

    @Test
    public void negativeTestWithString(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "test"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }

    @Test
    public void negativeTestWithNegativeNumber() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "-5"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }

    @Test
    public void negativeTestWithTooBigNumber(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "5000"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }

    @Test
    public void negativeTestWithDecimal(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "3.14"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }


}