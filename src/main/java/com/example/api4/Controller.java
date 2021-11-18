package com.example.api4;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
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
                .queryParam("mode", mode);

        return restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET,
                new HttpEntity(new HttpHeaders()), String.class).getBody();
    }

    @RequestMapping("/lowerCase/{string}")
    public String lowerCase(@PathVariable String string, @RequestParam(required = false) String mode) {
        return getResponseFromAnotherApi("/lowerCase/" + string, mode);
    }

    @RequestMapping("/upperCase/{string}")
    public String upperCase(@PathVariable String string, @RequestParam(required = false) String mode) {
        return getResponseFromAnotherApi("/upperCase/" + string, mode);
    }

    @RequestMapping("/numbers/{string}")
    public String numbers(@PathVariable String string, @RequestParam(required = false) String mode) {
        return getResponseFromAnotherApi("/numbers/" + string, mode);
    }

    @RequestMapping("/special/{string}")
    public String special(@PathVariable String string, @RequestParam(required = false) String mode) {
        return getResponseFromAnotherApi("/special/" + string, mode);
    }


}
