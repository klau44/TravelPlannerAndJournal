package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.travelplannerandjournal.model.Trip;
import pl.coderslab.travelplannerandjournal.model.TripRequest;
import pl.coderslab.travelplannerandjournal.model.TripResponse;
import pl.coderslab.travelplannerandjournal.repository.TripRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripResponse findById(Long id) {
        return tripRepository.findById(id)
                .map(TripResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));
    }

    public List<TripResponse> findAll() {
        return tripRepository.findAll().stream()
                .map(TripResponse::toDto)
                .toList();
    }

    public TripResponse add(TripRequest tripRequest) {
        Trip trip = Trip.builder()
                .name(tripRequest.getName())
                .destination(tripRequest.getDestination())
                .startDate(tripRequest.getStartDate())
                .endDate(tripRequest.getEndDate())
                .build();
        Trip saved = tripRepository.save(trip);

        return TripResponse.toDto(saved);
    }

    public TripResponse update(Long id, TripRequest tripRequest) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

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

    public void delete(Long id) {
        Trip tripToDelete = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        tripRepository.delete(tripToDelete);
    }

}
