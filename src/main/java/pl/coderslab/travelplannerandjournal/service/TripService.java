package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    public TripResponse findById(Long id, Authentication authentication) {
        User user = getUser(authentication);
        return tripRepository.findByIdAndUser(id, user)
                .map(TripResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));
    }

    public List<TripResponse> findAll(Authentication authentication) {
        User user = getUser(authentication);

        return tripRepository.findAllByUser(user).stream()
                .map(TripResponse::toDto)
                .toList();
    }

    public TripResponse add(TripRequest tripRequest, Authentication authentication) {
        User user = getUser(authentication);

        Trip trip = Trip.builder()
                .name(tripRequest.getName())
                .destination(tripRequest.getDestination())
                .startDate(tripRequest.getStartDate())
                .endDate(tripRequest.getEndDate())
                .user(user)
                .build();
        Trip saved = tripRepository.save(trip);

        return TripResponse.toDto(saved);
    }

    public TripResponse update(Long id, TripRequest tripRequest, Authentication authentication) {
        User user = getUser(authentication);

        Trip trip = tripRepository.findByIdAndUser(id, user)
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

        return TripResponse.toDto(updated);
    }

    public void delete(Long id, Authentication authentication) {
        User user = getUser(authentication);
        Trip tripToDelete = tripRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        tripRepository.delete(tripToDelete);
    }

    private User getUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
