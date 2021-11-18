package com.example.api4;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    private String getResponseFromAnotherApi(String string, String mode) {
        final String uri = "http://localhost:8081" + string;
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri)
                .queryParam("mode",mode);

        return restTemplate.exchange(builder.buildAndExpand().toUri() , HttpMethod.GET,
                new HttpEntity(new HttpHeaders()), String.class).getBody();
    }


}
