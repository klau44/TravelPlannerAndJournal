package pl.coderslab.travelplannerandjournal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.authorization.CustomUserDetails;
import pl.coderslab.travelplannerandjournal.model.AttractionDTO;
import pl.coderslab.travelplannerandjournal.model.TripAttractionResponse;
import pl.coderslab.travelplannerandjournal.service.AttractionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trips/{tripId}/attractions")
public class TripAttractionController {

    private final AttractionService attractionService;

    @PostMapping
    public ResponseEntity<TripAttractionResponse> addAttractionToTrip(@PathVariable Long tripId,
                                                                      @RequestBody AttractionDTO attractionDTO,
                                                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        TripAttractionResponse tripAttractionResponse = attractionService.addAttractionToTrip(attractionDTO, tripId, userId);

        return new ResponseEntity<>(tripAttractionResponse, HttpStatus.CREATED);
    }
}
