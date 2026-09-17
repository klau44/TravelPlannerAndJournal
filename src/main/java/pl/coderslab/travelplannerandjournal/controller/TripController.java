package pl.coderslab.travelplannerandjournal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.authorization.CustomUserDetails;
import pl.coderslab.travelplannerandjournal.model.entity.TripRequest;
import pl.coderslab.travelplannerandjournal.model.dto.TripResponse;
import pl.coderslab.travelplannerandjournal.service.TripService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripResponse>> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return new ResponseEntity<>(tripService.findAll(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findById(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return new ResponseEntity<>(tripService.findById(id, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TripResponse> add(@RequestBody TripRequest tripRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return new ResponseEntity<>(tripService.add(tripRequest, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> update(@PathVariable Long id, @RequestBody TripRequest tripRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        return new ResponseEntity<>(tripService.update(id, tripRequest, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        tripService.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}
