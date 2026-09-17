package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.travelplannerandjournal.model.dto.TripHistoryResponse;
import pl.coderslab.travelplannerandjournal.model.entity.Trip;
import pl.coderslab.travelplannerandjournal.model.dto.TripRequest;
import pl.coderslab.travelplannerandjournal.model.dto.CreateTripResponse;
import pl.coderslab.travelplannerandjournal.model.entity.User;
import pl.coderslab.travelplannerandjournal.repository.TripRepository;
import pl.coderslab.travelplannerandjournal.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public CreateTripResponse findById(Long tripId, Long userId) {
        return tripRepository.findByIdAndUserId(tripId, userId)
                .map(CreateTripResponse::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));
    }

    public List<TripHistoryResponse> findAll(Long userId) {
        return tripRepository.findAllByUserId(userId).stream()
                .map(TripHistoryResponse::toResponse)
                .toList();
    }

    public CreateTripResponse add(TripRequest tripRequest, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Trip trip = Trip.builder()
                .name(tripRequest.getName())
                .destination(tripRequest.getDestination())
                .startDate(tripRequest.getStartDate())
                .endDate(tripRequest.getEndDate())
                .user(user)
                .build();
        Trip saved = tripRepository.save(trip);

        return CreateTripResponse.toResponse(saved);
    }

    public CreateTripResponse update(Long tripId, TripRequest tripRequest, Long userId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        if (tripRequest.getName() != null) {
            trip.setName(tripRequest.getName());
        }

        if (tripRequest.getDestination() != null) {
            trip.setDestination(tripRequest.getDestination());
        }

        if (tripRequest.getStartDate() != null) {
            trip.setStartDate(tripRequest.getStartDate());
        }

        if (tripRequest.getEndDate() != null) {
            trip.setEndDate(tripRequest.getEndDate());
        }

        Trip updated = tripRepository.save(trip);

        return CreateTripResponse.toResponse(updated);
    }

    public void delete(Long tripId, Long userId) {
        Trip tripToDelete = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        tripRepository.delete(tripToDelete);
    }
}
