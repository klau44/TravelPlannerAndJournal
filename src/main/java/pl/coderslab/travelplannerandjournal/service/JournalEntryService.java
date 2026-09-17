package pl.coderslab.travelplannerandjournal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.travelplannerandjournal.model.dto.JournalEntryRequest;
import pl.coderslab.travelplannerandjournal.model.dto.JournalEntryResponse;
import pl.coderslab.travelplannerandjournal.model.entity.JournalEntry;
import pl.coderslab.travelplannerandjournal.model.entity.Trip;
import pl.coderslab.travelplannerandjournal.repository.JournalEntryRepository;
import pl.coderslab.travelplannerandjournal.repository.TripRepository;

@RequiredArgsConstructor
@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final TripRepository tripRepository;

    public JournalEntryResponse addJournalEntry(JournalEntryRequest journalEntryRequest, Long tripId, Long userId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        JournalEntry entry = JournalEntry.builder()
                .content(journalEntryRequest.getContent())
                .trip(trip)
                .build();

        JournalEntry saved = journalEntryRepository.save(entry);trip.setJournalEntry(saved);
        return JournalEntryResponse.toResponse(saved);
    }

    public JournalEntryResponse showTripJournalEntry(Long tripId, Long userId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found for the user"));

        JournalEntry entry = journalEntryRepository.findByTripId(trip.getId())
                .orElseThrow(() -> new EntityNotFoundException("Journal entry not found"));

        return JournalEntryResponse.toResponse(entry);
    }
}
