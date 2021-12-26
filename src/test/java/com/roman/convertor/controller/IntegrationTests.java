package com.roman.convertor.controller;

import com.roman.convertor.ConverterApplication;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
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
        return String.format("http://localhost:%d%s", portNumber, uri);
    }

    /***
     * passing correct input as 2021, expected to receive status code 200 and {input: 2021, output:MMXXI}
     */
    @Test
    @DisplayName("Happy test case")
    public void positiveTestWithCorrectNumber() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "2021"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"input\":\"2021\",\"output\":\"MMXXI\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    /***
     * didn't pass request param and expecting to receive 400 as bad request exception
     */
    @Test
    @DisplayName("negative test case with empty request")
    public void negativeTestWithEmptyRequest() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }
    /***
     * passed string input and expecting to receive 400 and bad request exception
     */
    @Test
    @DisplayName("negative test case - passed string but expecting number")
    public void negativeTestWithString() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "test"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }
    /***
     * passed wrong number -5 which is not valid range, so expected to receive 400 and bad request exception
     */
    @Test
    @DisplayName("negative test case - input is out of range")
    public void negativeTestWithNegativeNumber() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "-5"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }
    /***
     * passed wrong number 5000 which is not valid range, so expected to receive 400 and bad request exception
     */
    @Test
    @DisplayName("negative test case - input is out of range")
    public void negativeTestWithTooBigNumber() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "5000"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }
    /***
     * passed wrong number 3.14 which not valid number
     */
    @Test
    @DisplayName("negative test case - passed decimal but expected to receive valid number")
    public void negativeTestWithDecimal() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLPort(ENDPOINT + "3.14"),
                HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue(), is(400));
    }


}