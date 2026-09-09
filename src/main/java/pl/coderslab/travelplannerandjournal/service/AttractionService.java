package pl.coderslab.travelplannerandjournal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AttractionService {

    private final RestClient restClient;
    private final String apiKey;

    public AttractionService(RestClient restClient,
                             @Value("${geoapify.api-key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public String getPlaceGeocode (String city) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/geocode/search")
                        .queryParam("text", city)
                        .queryParam("type", "city")
                        .queryParam("limit", 1)
                        .queryParam("lang", "pl")
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .body(String.class);
    }
}