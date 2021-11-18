package com.example.api4;

import org.json.JSONObject;
import org.json.XML;
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

    @RequestMapping("/convert")
    public String convert(@RequestBody String string, @RequestParam(required = false) String inputMode, @RequestParam(required = false) String outputMode) {
        int result = 0;
        if (inputMode.equals("json")) {
            JSONObject js = new JSONObject(string);
            result = js.getInt("result");
        }
        if (inputMode.equals("xml")) {
            JSONObject js = XML.toJSONObject(string);
            result = js.getInt("result");
        }
        if (inputMode.equals("csv")){
            String num = string.substring(8);
            result = Integer.parseInt(num);
        }
        if (inputMode.equals("txt")) {
            String[] temp = string.split(" ");
            result = Integer.parseInt(temp[1]);
        }


        if (outputMode.equals("json")) {
            return new JSONObject("{ \"result\" : " + result + " }").toString();
        }
        if (outputMode.equals("xml")) {
            return "<result>" + result + "<result>";
        }
        if (outputMode.equals("csv")){
            return "\"result\"\n" + result;
        }
        if (outputMode.equals("txt")) {
            return "result: " + result;
        }


        return Integer.toString(result);
    }

}
