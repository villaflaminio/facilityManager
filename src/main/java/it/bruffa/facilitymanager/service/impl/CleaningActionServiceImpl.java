package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.repository.CheckListRepository;
import it.bruffa.facilitymanager.repository.CleaningActionRepository;
import it.bruffa.facilitymanager.repository.RoleRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import it.bruffa.facilitymanager.service.CleaningActionService;
import it.bruffa.facilitymanager.utilities.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CleaningActionServiceImpl implements CleaningActionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @Autowired
    private CleaningActionRepository cleaningActionRepository;


    @Autowired
    private CheckListRepository checkListRepository;

    public List<CleaningAction> assignCleaningAction(LocalDate checkOutDate) {

        Role cleanerRole = roleRepository.findByName("ROLE_CLEANER").orElseThrow(() -> new RuntimeException("Role not found"));

        CheckList checkList = checkListRepository.findByName("CLEANER").orElseThrow(() -> new RuntimeException("CheckList not found"));

        List<Reservation> reservationsWithCleaningNeeds = reservationServiceImpl.getReservationsWithCheckOnDay(checkOutDate);
        List<User> cleaners = userRepository.findByRoles(cleanerRole);

        //copy cleaners to new list

        List<CleaningAction> cleaningActions = new ArrayList<>();
        List<User> cleanersToEvaluate = new ArrayList<>(cleaners);

        for (Reservation reservation : reservationsWithCleaningNeeds) {

            if (cleanersToEvaluate.size() == 0) {
                cleanersToEvaluate = new ArrayList<>(cleaners);
            }

            Structure structure = reservation.getStructure();
            User nearestCleaner = null;
            double minDistance = Double.MAX_VALUE;

            for (User cleaner : cleanersToEvaluate) {
                double distance = GeoUtils.getDistanceBetweenPointsNew(structure.getLatitude(), structure.getLongitude(), cleaner.getLatitude(), cleaner.getLongitude(), false);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCleaner = cleaner;
                }
            }
            cleanersToEvaluate.remove(nearestCleaner);
            System.out.println("Nearest cleaner: " + nearestCleaner.getFirstName() + " " + nearestCleaner.getLastName() + " to structure: " + structure.getName());
            CleaningAction cleaningAction = CleaningAction.builder()
                    .user(nearestCleaner)
                    .checkList(checkList)
                    .date(checkOutDate)
                    .structure(structure)
                    .build();

            cleaningActions.add(cleaningAction);
        }
        return cleaningActions;
    }


}
