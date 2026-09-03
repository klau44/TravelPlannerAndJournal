package pl.coderslab.travelplannerandjournal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.model.TripRequest;
import pl.coderslab.travelplannerandjournal.model.TripResponse;
import pl.coderslab.travelplannerandjournal.service.TripService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripResponse>> findAll() {
        return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(tripService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TripResponse> add(@RequestBody TripRequest tripRequest) {
        return new ResponseEntity<>(tripService.add(tripRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> update(@PathVariable Long id, @RequestBody TripRequest tripRequest) {
        return new ResponseEntity<>(tripService.update(id, tripRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
