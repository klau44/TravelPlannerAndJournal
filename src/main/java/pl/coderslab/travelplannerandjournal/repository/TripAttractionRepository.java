package pl.coderslab.travelplannerandjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.travelplannerandjournal.model.TripAttraction;

@Repository
public interface TripAttractionRepository extends JpaRepository<TripAttraction, Long> {
}
