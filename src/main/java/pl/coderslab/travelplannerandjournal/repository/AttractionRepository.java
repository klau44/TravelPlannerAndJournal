package pl.coderslab.travelplannerandjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.travelplannerandjournal.model.entity.Attraction;

import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    Optional<Attraction> findByExternalId(String externalId);
}
