package pl.coderslab.travelplannerandjournal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.authorization.CustomUserDetails;
import pl.coderslab.travelplannerandjournal.model.AttractionDTO;
import pl.coderslab.travelplannerandjournal.model.TripAttractionResponse;
import pl.coderslab.travelplannerandjournal.model.TripPlanDto;
import pl.coderslab.travelplannerandjournal.service.TripAttractionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trips/{tripId}/trip-attractions")
public class TripAttractionController {

    private final TripAttractionService tripAttractionService;

    @PostMapping
    public ResponseEntity<TripAttractionResponse> addAttractionToTrip(@PathVariable Long tripId,
                                                                      @RequestBody AttractionDTO attractionDTO,
                                                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        TripAttractionResponse tripAttractionResponse = tripAttractionService.addAttractionToTrip(attractionDTO, tripId, userId);

        return new ResponseEntity<>(tripAttractionResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TripPlanDto> showTripPlan(@PathVariable Long tripId,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        TripPlanDto tripPlan = tripAttractionService.showTripAttractions(tripId, userId);

        return new ResponseEntity<>(tripPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{tripAttractionId}")
    public ResponseEntity<Void> deleteTripAttraction(@PathVariable Long tripId, @PathVariable Long tripAttractionId,
                                                     @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        tripAttractionService.deleteTripAttraction(tripId, tripAttractionId, userId);

        return ResponseEntity.noContent().build();
    }
}
