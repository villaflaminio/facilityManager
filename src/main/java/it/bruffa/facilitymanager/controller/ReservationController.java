package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
@Tag(name = "ReservationController", description = "The reservation APIs")
public interface ReservationController {
}
