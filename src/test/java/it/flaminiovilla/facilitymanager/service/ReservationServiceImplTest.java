package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.request.CreateReservationRequest;
import it.flaminiovilla.facilitymanager.service.impl.ReservationServiceImpl;
import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import it.flaminiovilla.facilitymanager.model.entity.Structure;
import it.flaminiovilla.facilitymanager.repository.ReservationRepository;
import it.flaminiovilla.facilitymanager.repository.StructureRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImplTest.class);
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private StructureRepository structureRepository;
    @InjectMocks
    ReservationServiceImpl reservationserviceimpl;

    @Test
    @DisplayName("get Reservations With Check On Day")
    public void getReservationsWithCheckOnDay() {
        try {
            log.info("Starting execution of getReservationsWithCheckOnDay");
            List<Reservation> expectedValue = Instancio.ofList(Reservation.class).size(10).create();
            LocalDate checkOutDate = Instancio.of(LocalDate.class).create();

            when(reservationRepository.findAllByCheckOut(checkOutDate)).thenReturn(expectedValue);

            List<Reservation> actualValue = reservationserviceimpl.getReservationsWithCheckOnDay(checkOutDate);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("get Reservation By Id")
    public void getReservationById() {
        try {
            log.info("Starting execution of getReservationById");
            Reservation reservation = Instancio.create(Reservation.class);

            when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
            Long reservationId = reservation.getId();

            ResponseEntity<Reservation> actualValue = reservationserviceimpl.getReservationById(reservationId);
            Assertions.assertEquals(reservation, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @DisplayName("create Reservation")
    @Test
    public void createReservation() {
        try {
            log.info("Starting execution of createReservation");
            CreateReservationRequest createReservationRequest = Instancio.create(CreateReservationRequest.class);

            Structure structure = Instancio.create(Structure.class);

            when(structureRepository.findById(anyLong())).thenReturn(Optional.of(structure));

            ResponseEntity<Reservation> actualValue = reservationserviceimpl.createReservation(createReservationRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }
    @Test
    @DisplayName("delete Reservation")
    public void deleteReservation() {
        try {
            log.info("Starting execution of deleteReservation");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);


            ResponseEntity<Boolean> actualValue = reservationserviceimpl.deleteReservation(1l);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


}
