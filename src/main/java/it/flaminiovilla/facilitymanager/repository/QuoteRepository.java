package it.flaminiovilla.facilitymanager.repository;

import it.flaminiovilla.facilitymanager.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}