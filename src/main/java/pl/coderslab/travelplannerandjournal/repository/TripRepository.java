package pl.coderslab.travelplannerandjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.travelplannerandjournal.model.Trip;
import pl.coderslab.travelplannerandjournal.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByUser(User user);

    Optional<Trip> findByIdAndUser(Long id, User user);
}
