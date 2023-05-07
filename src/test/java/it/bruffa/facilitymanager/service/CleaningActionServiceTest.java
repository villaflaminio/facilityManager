package it.bruffa.facilitymanager.service;


import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.CleaningActionServiceImpl;
import it.bruffa.facilitymanager.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleaningActionServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CheckListRepository checkListRepository;

    @Mock
    private CleaningActionRepository cleaningActionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private CleaningActionServiceImpl cleaningActionService;

    @Mock
    private ReservationServiceImpl reservationServiceImpl;

    Role roleCleaner = new Role();
    CheckList checkList = new CheckList();
    LocalDate checkOut = LocalDate.of(2021, 1, 1);
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    User user4 = new User();
    List<User> cleaners = new ArrayList<>();


    Structure structure1 = new Structure();
    Structure structure2 = new Structure();
    Structure structure3 = new Structure();
    Structure structure4 = new Structure();
    Structure structure5 = new Structure();
    Structure structure6 = new Structure();
    Structure structure7 = new Structure();


    Reservation reservation1 = new Reservation();
    Reservation reservation2 = new Reservation();
    Reservation reservation3 = new Reservation();
    Reservation reservation4 = new Reservation();
    Reservation reservation5 = new Reservation();
    Reservation reservation6 = new Reservation();
    Reservation reservation7 = new Reservation();

    List<Reservation> reservations = new ArrayList<>();

    @BeforeEach
    void setUp() {
        roleCleaner.setId(1L);
        roleCleaner.setName("ROLE_CLEANER");

        user1.setId(1L);
        user1.setFirstName("user1");
        user1.setLastName("eur");
        user1.setEmail("user1@gmail.com");
        user1.setLatitude(41.82881948167025);
        user1.setLongitude(12.47105618287844);

        user2.setId(2L);
        user2.setFirstName("user2");
        user2.setLastName("castelSantAngelo");
        user2.setEmail("user2@gmail.com");
        user2.setLatitude(41.90321481596937);
        user2.setLongitude(12.466329625229484);

        user3.setId(3L);
        user3.setFirstName("user3");
        user3.setLastName("elis");
        user3.setEmail("user3@gmail.com");
        user3.setLatitude(41.90663196282034);
        user3.setLongitude(12.549319304895146);

        user4.setId(4L);
        user4.setFirstName("user4");
        user4.setLastName("ostia");
        user4.setEmail("user4@gmail.com");
        user4.setLatitude(41.737130533207726);
        user4.setLongitude(12.285132476685916);


        structure1.setId(1L);
        structure1.setAddress("Via Giovanni Martinotti, 20, 00135 Roma RM");
        structure1.setLatitude(41.952405105333355);
        structure1.setLongitude(12.415838231732067);
        structure1.setName("Ospedale San Filippo Neri - 1");
        structure1.setCleaningDuration(1);

        structure2.setId(2L);
        structure2.setAddress("Via dei Badoer, 5, 00148 Roma RM");
        structure2.setName("Policlinico Luigi di Liegro - 2");
        structure2.setLatitude(41.85888609469103);
        structure2.setLongitude(12.427167898523873);
        structure2.setCleaningDuration(2);

        structure3.setId(3L);
        structure3.setAddress("Via di Grottarossa, 1035, 00189 Roma RM");
        structure3.setName("Ospedale Sant'Andrea - 3");
        structure3.setLatitude(41.986354850396275);
        structure3.setLongitude(12.471799854884132);
        structure3.setCleaningDuration(3);

        structure4.setId(4L);
        structure4.setAddress("Via Casilina, 1049, 00169 Roma RM");
        structure4.setLatitude(41.87473775030915);
        structure4.setLongitude(12.591619481973181);
        structure4.setName("Policlinico Casilino - 4");
        structure4.setCleaningDuration(4);

        structure5.setId(5L);
        structure5.setAddress("Via Prenestina, 325, 00171 Roma RM");
        structure5.setLatitude(41.897741222803695);
        structure5.setLongitude(12.550764079040238);
        structure5.setName("Villa Gordiani - 5");
        structure5.setCleaningDuration(5);

        structure6.setId(6L);
        structure6.setAddress("00054 Fiumicino RM");
        structure6.setLatitude(41.808748560516484);
        structure6.setLongitude(12.25275994283459);
        structure6.setName("Aeroporto internazionale Leonardo da Vinci - 6");
        structure6.setCleaningDuration(6);

        structure7.setId(7L);
        structure7.setAddress("Via S. Francesco d'Assisi, 50, 00041 Albano Laziale RM");
        structure7.setLatitude(41.73280220023832);
        structure7.setLongitude(12.665194433877856);
        structure7.setName("Castel Gandolfo - 7");
        structure7.setCleaningDuration(7);

        reservation1.setId(1L);
        reservation1.setStructure(structure1);
        reservation1.setDeparture(checkOut);

        reservation2.setId(2L);
        reservation2.setStructure(structure2);
        reservation2.setDeparture(checkOut);

        reservation3.setId(3L);
        reservation3.setStructure(structure3);
        reservation3.setDeparture(checkOut);

        reservation4.setId(4L);
        reservation4.setStructure(structure4);
        reservation4.setDeparture(checkOut);

        reservation5.setId(5L);
        reservation5.setStructure(structure5);
        reservation5.setDeparture(checkOut);

        reservation6.setId(6L);
        reservation6.setStructure(structure6);
        reservation6.setDeparture(checkOut);

        reservation7.setId(7L);
        reservation7.setStructure(structure7);
        reservation7.setDeparture(checkOut);


        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);
        reservations.add(reservation4);
        reservations.add(reservation5);
        reservations.add(reservation6);
        reservations.add(reservation7);


        cleaners.add(user1);
        cleaners.add(user2);
        cleaners.add(user3);
        cleaners.add(user4);
    }

    @Test
    void getCleaners() {
        when(roleRepository.findByName("ROLE_CLEANER")).thenReturn(Optional.of(roleCleaner));
        when(checkListRepository.findByName("CLEANER")).thenReturn(Optional.of(checkList));
        when(userRepository.findByRoles(roleCleaner)).thenReturn(cleaners);
        when(reservationServiceImpl.getReservationsWithCheckOnDay(checkOut)).thenReturn(reservations);

        List<CleaningAction> cleanersTest = cleaningActionService.assignCleaningAction(checkOut);

        assertEquals(7, cleanersTest.size());


        Assertions.assertEquals(2L, cleanersTest.get(0).getUser().getId().longValue());
        Assertions.assertEquals(1L, cleanersTest.get(0).getStructure().getId().longValue());
        Assertions.assertEquals("Ospedale San Filippo Neri - 1", cleanersTest.get(0).getStructure().getName());


        Assertions.assertEquals(1L, cleanersTest.get(1).getUser().getId().longValue());
        Assertions.assertEquals(2L, cleanersTest.get(1).getStructure().getId().longValue());
        Assertions.assertEquals("Policlinico Luigi di Liegro - 2", cleanersTest.get(1).getStructure().getName());

        Assertions.assertEquals(3L, cleanersTest.get(2).getUser().getId().longValue());
        Assertions.assertEquals(3L, cleanersTest.get(2).getStructure().getId().longValue());
        Assertions.assertEquals("Ospedale Sant'Andrea - 3", cleanersTest.get(2).getStructure().getName());

        Assertions.assertEquals(4L, cleanersTest.get(3).getUser().getId().longValue());
        Assertions.assertEquals(4L, cleanersTest.get(3).getStructure().getId().longValue());
        Assertions.assertEquals("Policlinico Casilino - 4", cleanersTest.get(3).getStructure().getName());


        Assertions.assertEquals(3L, cleanersTest.get(4).getUser().getId().longValue());
        Assertions.assertEquals(5L, cleanersTest.get(4).getStructure().getId().longValue());
        Assertions.assertEquals("Villa Gordiani - 5", cleanersTest.get(4).getStructure().getName());

        Assertions.assertEquals(4L, cleanersTest.get(5).getUser().getId().longValue());
        Assertions.assertEquals(6L, cleanersTest.get(5).getStructure().getId().longValue());
        Assertions.assertEquals("Aeroporto internazionale Leonardo da Vinci - 6", cleanersTest.get(5).getStructure().getName());

        Assertions.assertEquals(1L, cleanersTest.get(6).getUser().getId().longValue());
        Assertions.assertEquals(7L, cleanersTest.get(6).getStructure().getId().longValue());
        Assertions.assertEquals("Castel Gandolfo - 7", cleanersTest.get(6).getStructure().getName());
    }

}
