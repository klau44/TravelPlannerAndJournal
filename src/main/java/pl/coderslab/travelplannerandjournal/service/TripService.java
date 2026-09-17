package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.travelplannerandjournal.model.Trip;
import pl.coderslab.travelplannerandjournal.model.TripRequest;
import pl.coderslab.travelplannerandjournal.model.TripResponse;
import pl.coderslab.travelplannerandjournal.model.User;
import pl.coderslab.travelplannerandjournal.repository.TripRepository;
import pl.coderslab.travelplannerandjournal.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public TripResponse findById(Long tripId, Long userId) {
        return tripRepository.findByIdAndUserId(tripId, userId)
                .map(TripResponse::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));
    }

    public List<TripResponse> findAll(Long userId) {
        return tripRepository.findAllByUserId(userId).stream()
                .map(TripResponse::toResponse)
                .toList();
    }

    public TripResponse add(TripRequest tripRequest, Long userId) {
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

        return TripResponse.toResponse(saved);
    }

    public TripResponse update(Long tripId, TripRequest tripRequest, Long userId) {
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

        return TripResponse.toResponse(updated);
    }

    public void delete(Long tripId, Long userId) {
        Trip tripToDelete = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        tripRepository.delete(tripToDelete);
    }
}
