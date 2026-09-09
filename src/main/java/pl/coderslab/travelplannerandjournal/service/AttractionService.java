package pl.coderslab.travelplannerandjournal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.coderslab.travelplannerandjournal.model.AttractionDTO;
import pl.coderslab.travelplannerandjournal.model.externalapi.GeocodeDTO;
import pl.coderslab.travelplannerandjournal.model.externalapi.PlaceDTO;

import java.util.List;

@Service
public class AttractionService {

    private final RestClient restClient;
    private final String apiKey;

    public AttractionService(RestClient restClient,
                             @Value("${geoapify.api-key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public List<AttractionDTO> findAttractions(String city) {
        String placeId = getPlaceId(city);
        PlaceDTO places = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/places")
                        .queryParam("categories", "tourism")
                        .queryParam("filter", "place:" + placeId)
                        .queryParam("limit", 100)
                        .queryParam("lang", "pl")
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .body(PlaceDTO.class);

        if (places == null || places.getFeatures() == null) {
            return List.of();
        }

        return places.getFeatures().stream()
                .map(feature -> AttractionDTO.builder()
                        .name(feature.getProperties().getName())
                        .categories(feature.getProperties().getCategories())
                        .build())
                .toList();
    }

    public String getPlaceId(String city) {
        GeocodeDTO geocode = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/geocode/search")
                        .queryParam("text", city)
                        .queryParam("type", "city")
                        .queryParam("limit", 1)
                        .queryParam("lang", "pl")
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .body(GeocodeDTO.class);

        if (geocode == null || geocode.getFeatures() == null || geocode.getFeatures().isEmpty()) {
            return null; //TODO throw exception
        }

        return geocode.getFeatures().get(0).getProperties().getPlaceId();
    }
}