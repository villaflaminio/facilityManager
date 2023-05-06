package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}