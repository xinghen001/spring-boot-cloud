package com.xinghen.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Value("${security.user.name}")
    private String username;
    @Value("${security.user.password}")
    private String password;

    @Test
    public void configurationAvailable() {
        ResponseEntity<Map> entity = testRestTemplate
                .withBasicAuth(username, password)
                .getForEntity("/app/cloud", Map.class);
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void envPostAvailable() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        ResponseEntity<Map> entity = testRestTemplate
                .withBasicAuth(username, password)
                .postForEntity("/admin/env", form, Map.class);
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
