package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CleaningActionService {
    @InjectMocks
    CleaningActionService cleaningActionService = new CleaningActionService();

    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    User user4 = new User();
    List<User> users = new ArrayList<>();


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
        user1.setId(1L);
        user1.setFirstName("Mario");
        user1.setLastName("Rossi-eur");
        user1.setEmail("mariorossi@gmail.com");
        user1.setLatitude(41.82881948167025);
        user1.setLongitude(12.47105618287844);

        user2.setId(2L);
        user2.setFirstName("Luigi");
        user2.setLastName("Verdi-castelSantAngelo");
        user2.setEmail("luigiverdi@gmail.com");
        user2.setLatitude(41.90321481596937);
        user2.setLongitude(12.466329625229484);

        user3.setId(3L);
        user3.setFirstName("Giovanni");
        user3.setLastName("Bianchi-elis");
        user3.setEmail("giovannibianchi@gmail.com");
        user3.setLatitude(41.90663196282034);
        user3.setLongitude(12.549319304895146);

        user4.setId(4L);
        user4.setFirstName("Giuseppe");
        user4.setLastName("Neri-ostia");
        user4.setEmail("giuseppeneri@gmail.com");
        user4.setLatitude(41.737130533207726);
        user4.setLongitude(12.285132476685916);


        structure1.setId(1L);
        structure1.setAddress("Via Giovanni Martinotti, 20, 00135 Roma RM");
        structure1.setLatitude(41.952405105333355);
        structure1.setLongitude(12.415838231732067);
        structure1.setName("Ospedale San Filippo Neri");
        structure1.setCleaningDuration(1);

        structure2.setId(2L);
        structure2.setAddress("Via dei Badoer, 5, 00148 Roma RM");
        structure2.setName("Policlinico Luigi di Liegro\n");
        structure2.setLatitude(41.85888609469103);
        structure2.setLongitude(12.427167898523873);
        structure2.setCleaningDuration(2);

        structure3.setId(3L);
        structure3.setAddress("Via di Grottarossa, 1035, 00189 Roma RM");
        structure3.setName("Ospedale Sant'Andrea");
        structure3.setLatitude(41.986354850396275);
        structure3.setLongitude(12.471799854884132);
        structure3.setCleaningDuration(3);

        structure4.setId(4L);
        structure4.setAddress("Via Casilina, 1049, 00169 Roma RM");
        structure4.setLatitude(41.87473775030915);
        structure4.setLongitude(12.591619481973181);
        structure4.setName("Policlinico Casilino");
        structure4.setCleaningDuration(4);

        structure5.setId(5L);
        structure5.setAddress("Via Prenestina, 325, 00171 Roma RM");
        structure5.setLatitude(41.897741222803695);
        structure5.setLongitude(12.550764079040238);
        structure5.setName("Villa Gordiani");
        structure5.setCleaningDuration(5);

        structure6.setId(6L);
        structure6.setAddress("00054 Fiumicino RM");
        structure6.setLatitude(41.808748560516484);
        structure6.setLongitude(12.25275994283459);
        structure6.setName("Aeroporto internazionale Leonardo da Vinci\n");
        structure6.setCleaningDuration(6);

        structure7.setId(7L);
        structure7.setAddress("Via S. Francesco d'Assisi, 50, 00041 Albano Laziale RM");
        structure7.setLatitude(41.73280220023832);
        structure7.setLongitude(12.665194433877856);
        structure7.setName("Castel Gandolfo");
        structure7.setCleaningDuration(7);

        reservation1.setId(1L);
        reservation1.setStructure(structure1);
        reservation1.setCheckOut(LocalDateTime.of(2021, 5, 10, 10, 0));

        reservation2.setId(2L);
        reservation2.setStructure(structure2);
        reservation2.setCheckOut(LocalDateTime.of(2021, 5, 10, 16, 0));

        reservation3.setId(3L);
        reservation3.setStructure(structure3);
        reservation3.setCheckOut(LocalDateTime.of(2021, 5, 10, 8, 30));

        reservation4.setId(4L);
        reservation4.setStructure(structure4);
        reservation4.setCheckOut(LocalDateTime.of(2021, 5, 10, 6, 0));

        reservation5.setId(5L);
        reservation5.setStructure(structure5);
        reservation5.setCheckOut(LocalDateTime.of(2021, 5, 10, 18, 0));

        reservation6.setId(6L);
        reservation6.setStructure(structure6);
        reservation6.setCheckOut(LocalDateTime.of(2021, 5, 10, 5, 30));

        reservation7.setId(7L);
        reservation7.setStructure(structure7);
        reservation7.setCheckOut(LocalDateTime.of(2021, 5, 10, 12, 0));


        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);
        reservations.add(reservation4);
        reservations.add(reservation5);
        reservations.add(reservation6);
        reservations.add(reservation7);
    }


}
