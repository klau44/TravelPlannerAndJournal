package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import pl.coderslab.travelplannerandjournal.model.*;
import pl.coderslab.travelplannerandjournal.model.externalapi.GeocodeDTO;
import pl.coderslab.travelplannerandjournal.model.externalapi.PlaceDTO;
import pl.coderslab.travelplannerandjournal.repository.AttractionRepository;
import pl.coderslab.travelplannerandjournal.repository.TripAttractionRepository;
import pl.coderslab.travelplannerandjournal.repository.TripRepository;

import java.util.List;

@Service
public class AttractionService {

    private final RestClient restClient;
    private final String apiKey;
    private final AttractionRepository attractionRepository;
    private final CategoryService categoryService;
    private final TripRepository tripRepository;
    private final TripAttractionRepository tripAttractionRepository;

    public AttractionService(RestClient restClient, @Value("${geoapify.api-key}") String apiKey,
                             AttractionRepository attractionRepository, CategoryService categoryService, TripRepository tripRepository, TripAttractionRepository tripAttractionRepository) {
        this.restClient = restClient;
        this.apiKey = apiKey;
        this.attractionRepository = attractionRepository;
        this.categoryService = categoryService;
        this.tripRepository = tripRepository;
        this.tripAttractionRepository = tripAttractionRepository;
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
                        .country(feature.getProperties().getCountry())
                        .city(feature.getProperties().getCity())
                        .postcode(feature.getProperties().getPostcode())
                        .address(feature.getProperties().getAddress())
                        .categories(feature.getProperties().getCategories())
                        .externalId(feature.getProperties().getExternalId())
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

    @Transactional
    public TripAttractionResponse addAttractionToTrip(AttractionDTO attractionDTO, Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        Attraction attraction = attractionRepository.findByExternalId(attractionDTO.getExternalId())
                .orElseGet(() -> createAttraction(attractionDTO));

        TripAttraction tripAttraction = TripAttraction.builder()
                .trip(trip)
                .attraction(attraction)
                .visited(false)
                .build();

        tripAttractionRepository.save(tripAttraction);

        return TripAttractionResponse.builder()
                .tripName(trip.getName())
                .tripDestination(trip.getDestination())
                .tripStartDate(trip.getStartDate())
                .tripEndDate(trip.getEndDate())
                .attractionName(attraction.getName())
                .attractionAddress(attraction.getAddress())
                .build();
    }

    private Attraction createAttraction(AttractionDTO attractionDTO) {
        List<Category> categories = attractionDTO.getCategories().stream()
                .map(categoryService::findOrCreateCategory)
                .toList();

        Attraction attraction = Attraction.builder()
                .name(attractionDTO.getName())
                .country(attractionDTO.getCountry())
                .city(attractionDTO.getCity())
                .postcode(attractionDTO.getPostcode())
                .address(attractionDTO.getAddress())
                .categories(categories)
                .externalId(attractionDTO.getExternalId())
                .build();

        return attractionRepository.save(attraction);
    }

}