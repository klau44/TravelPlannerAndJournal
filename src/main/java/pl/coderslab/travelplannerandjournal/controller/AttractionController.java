package pl.coderslab.travelplannerandjournal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.travelplannerandjournal.model.dto.AttractionDto;
import pl.coderslab.travelplannerandjournal.service.AttractionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/search")
    public ResponseEntity<List<AttractionDto>> findAttractions(@RequestParam String location) {
        return ResponseEntity.ok(attractionService.findAttractions(location));
    }

}
