package pl.coderslab.travelplannerandjournal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.authorization.CustomUserDetails;
import pl.coderslab.travelplannerandjournal.model.dto.JournalEntryRequest;
import pl.coderslab.travelplannerandjournal.model.dto.JournalEntryResponse;
import pl.coderslab.travelplannerandjournal.service.JournalEntryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trips/{tripId}/journal-entry")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<JournalEntryResponse> addJournalEntry(@PathVariable Long tripId, @Valid @RequestBody JournalEntryRequest request,
                                                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        JournalEntryResponse journalEntryResponse = journalEntryService.addJournalEntry(request, tripId, userDetails.getUser().getId());

        return new ResponseEntity<>(journalEntryResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<JournalEntryResponse> showJournalEntry(@PathVariable Long tripId,
                                                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        JournalEntryResponse journalEntryResponse = journalEntryService.showTripJournalEntry(tripId, userDetails.getUser().getId());

        return new ResponseEntity<>(journalEntryResponse, HttpStatus.OK);
    }
}
