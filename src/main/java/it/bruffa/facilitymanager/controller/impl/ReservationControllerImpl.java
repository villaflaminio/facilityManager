package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.ReservationController;
import it.bruffa.facilitymanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationControllerImpl implements ReservationController {
    @Autowired
    private ReservationService reservationService;
}
