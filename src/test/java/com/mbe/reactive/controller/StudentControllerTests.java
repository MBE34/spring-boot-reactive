package com.mbe.reactive.controller;

import com.mbe.reactive.ReactiveApplication;
import com.mbe.reactive.entities.Student;
import org.json.JSONException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ReactiveApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    TestRestTemplate restTemplate = new TestRestTemplate();

    final String BASE_URL = "http://localhost:8080";

    @Autowired
    WebTestClient webTestClient;

    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl(BASE_URL)
                .build();
    }

    @Test
    public void testRetrieveStudentById_usingRestTemplateTest() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/students/1"), HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1,\"firstName\":\"MBE\",\"lastName\":\"MBE\", \"age\":49}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testRetrieveStudentById_usingWebClient() throws JSONException {


        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseSpec response = webTestClient.get()
                .uri("/api/v1/students/1")
                .exchange();

        String expected = "{\"id\":1,\"firstName\":\"MBE\",\"lastName\":\"MBE\",\"age\":49}";
        Student exceptedStudent = new Student(1L, "MBE", "MBE", 49);
        response.expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo(expected);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
