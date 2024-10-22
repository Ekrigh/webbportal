package com.group6.webbportal.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConversionService {

    @Value("${openexchangerates.api.url}")
    private String apiUrl;

    @Value("${openexchangerates.api.appid}")
    private String appId;

    private final RestTemplate restTemplate;

    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double convertSEKToUSD(double priceInSEK) {
        // URL for fetching latest exchange rates with USD as base (default)
        String url = apiUrl + "?app_id=" + appId;

        // Make the API call
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        // Extract the rates from the response
        Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");
        Double sekRate = (Double) rates.get("SEK"); // SEK to USD rate

        // Perform the conversion: SEK to USD
        return priceInSEK / sekRate;
    }

    public double convertSEKToEUR(double priceInSEK) {
        // URL for fetching latest exchange rates with USD as base (default)
        String url = apiUrl + "?app_id=" + appId;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");
        Double sekRate = (Double) rates.get("SEK"); // SEK rate
        Double eurRate = (Double) rates.get("EUR"); // EUR rate

        return priceInSEK * (eurRate / sekRate);
    }
    public double convertSEKtoYEN(double priceInSEK){
        String url = apiUrl + "?app_id=" + appId;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");
        Double sekRate = (Double) rates.get("SEK"); // SEK rate
        Double yenRate = (Double) rates.get("JPY"); // YEN rate

        return priceInSEK * (yenRate / sekRate);
    }
}
