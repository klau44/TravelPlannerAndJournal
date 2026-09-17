package pl.coderslab.travelplannerandjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.travelplannerandjournal.model.entity.TripAttraction;

import java.util.List;

@Repository
public interface TripAttractionRepository extends JpaRepository<TripAttraction, Long> {

    List<TripAttraction> findAllByTripId(Long tripId);
}
