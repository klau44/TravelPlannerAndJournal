package pl.coderslab.travelplannerandjournal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.travelplannerandjournal.model.AttractionDTO;
import pl.coderslab.travelplannerandjournal.service.AttractionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public ResponseEntity<List<AttractionDTO>> findAttractions() {
        return ResponseEntity.ok(attractionService.findAttractions("Krasnystaw"));
    }

}
