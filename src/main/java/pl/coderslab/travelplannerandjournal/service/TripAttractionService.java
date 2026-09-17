package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.travelplannerandjournal.model.*;
import pl.coderslab.travelplannerandjournal.repository.AttractionRepository;
import pl.coderslab.travelplannerandjournal.repository.TripAttractionRepository;
import pl.coderslab.travelplannerandjournal.repository.TripRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripAttractionService {

    private final AttractionRepository attractionRepository;
    private final CategoryService categoryService;
    private final TripRepository tripRepository;
    private final TripAttractionRepository tripAttractionRepository;

    @Transactional
    public TripAttractionResponse addAttractionToTrip(AttractionDTO attractionDTO, Long tripId, Long userId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        Attraction attraction = attractionRepository.findByExternalId(attractionDTO.getExternalId())
                .orElseGet(() -> createAttraction(attractionDTO));

        TripAttraction tripAttraction = TripAttraction.builder()
                .trip(trip)
                .attraction(attraction)
                .visited(false)
                .build();

        TripAttraction saved = tripAttractionRepository.save(tripAttraction);

        return TripAttractionResponse.builder()
                .id(saved.getId())
                .attractionName(attraction.getName())
                .attractionAddress(attraction.getAddress())
                .visited(false)
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

    public TripPlanDto showTripAttractions(Long tripId, Long userId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        List<TripAttraction> tripAttractions = tripAttractionRepository.findAllByTripId(tripId);

        return TripPlanDto.builder()
                .tripName(trip.getName())
                .tripDestination(trip.getDestination())
                .tripStartDate(trip.getStartDate())
                .tripEndDate(trip.getEndDate())
                .tripAttractions(tripAttractions.stream()
                        .map(tripAttraction -> TripAttractionResponse.builder()
                                .attractionName(tripAttraction.getAttraction().getName())
                                .attractionAddress(tripAttraction.getAttraction().getAddress())
                                .visited(tripAttraction.isVisited())
                                .build())
                        .toList())
                .build();
    }

    public void deleteTripAttraction(Long tripId, Long tripAttractionId, Long userId) {
        tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        TripAttraction toDelete = tripAttractionRepository.findById(tripAttractionId)
                .orElseThrow();

        tripAttractionRepository.delete(toDelete);
    }
}
