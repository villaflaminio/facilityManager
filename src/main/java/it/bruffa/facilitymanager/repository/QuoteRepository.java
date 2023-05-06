package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}