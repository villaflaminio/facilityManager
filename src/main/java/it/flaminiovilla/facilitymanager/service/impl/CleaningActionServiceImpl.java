package it.flaminiovilla.facilitymanager.service.impl;

import it.flaminiovilla.facilitymanager.model.builder.CleaningActionBuilder;
import it.flaminiovilla.facilitymanager.model.builder.FileBuilder;
import it.flaminiovilla.facilitymanager.model.dto.CleaningActionFilter;
import it.flaminiovilla.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.entity.*;
import it.flaminiovilla.facilitymanager.model.projection.CleaningActionInfo;
import it.flaminiovilla.facilitymanager.service.CleaningActionService;
import it.flaminiovilla.facilitymanager.utilities.GeoUtils;
import it.flaminiovilla.facilitymanager.utilities.PropertiesHelper;
import it.flaminiovilla.facilitymanager.utilities.ResponseFile;
import it.flaminiovilla.facilitymanager.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    private StructureRepository structureRepository;

    @Autowired
    private CheckListRepository checkListRepository;

    @Autowired
    private FileRepository fileRepository;

    private static final Logger logger = LoggerFactory.getLogger(CleaningActionServiceImpl.class);

    @Override
    public ResponseEntity<Page<CleaningAction>> filter(CleaningActionFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        Pageable pageable;

        // Create a new probe with only the not null fields
        CleaningAction filter = new CleaningAction();

        if (probe.getUserId() != null)
            filter.setUser(userRepository.findById(probe.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));

        if (probe.getStructureId() != null)
            filter.setStructure(structureRepository.findById(probe.getStructureId()).orElseThrow(() -> new RuntimeException("Structure not found")));

        if (probe.getCheckListId() != null)
            filter.setCheckList(checkListRepository.findById(probe.getCheckListId()).orElseThrow(() -> new RuntimeException("CheckList not found")));

        PropertiesHelper.copyNonNullProperties(probe, filter);


        if (StringUtils.isEmpty(sortField)) {
            pageable = PageRequest.of(page, size);
        } else {

            Sort.Direction dir = StringUtils.isEmpty(sortDirection) ? Sort.Direction.ASC : Sort.Direction.valueOf(sortDirection.trim().toUpperCase());
            pageable = PageRequest.of(page, size, dir, sortField);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<CleaningAction> example = Example.of(filter, matcher);

        return ResponseEntity.ok(cleaningActionRepository.findAll(example, pageable));
    }

    @Override
    public ResponseEntity<CleaningActionInfo> getCleaningActionById(Long cleaningActionId) {
        logger.info("getCleaningActionById() called with id: {}", cleaningActionId);
        CleaningActionInfo action = cleaningActionRepository.findCleaningActionInfoById(cleaningActionId).orElseThrow(() -> new RuntimeException("CleaningAction not found"));
        return ResponseEntity.ok(action);
    }

    @Override
    public ResponseEntity<CleaningAction> createCleaningAction(CreateCleaningActionRequest createCleaningActionRequest) {
        try {
            logger.info("createCleaningAction() called with CheckList: {}", createCleaningActionRequest);
            CheckList checkList = checkListRepository.findById(createCleaningActionRequest.getCheckListId()).orElseThrow(() -> new RuntimeException("CheckList not found"));
            User userCleaner = userRepository.findById(createCleaningActionRequest.getUserCleanerId()).orElseThrow(() -> new RuntimeException("User not found"));
            Structure structure = structureRepository.findById(createCleaningActionRequest.getStructureId()).orElseThrow(() -> new RuntimeException("Structure not found"));


            CleaningAction newClean = CleaningActionBuilder.builder()
                    .checkList(checkList)
                    .user(userCleaner)
                    .structure(structure)
                    .date(createCleaningActionRequest.getDate())
                    .note(createCleaningActionRequest.getNote())
                    .report(createCleaningActionRequest.getReport())
                    .cleaningDuration(createCleaningActionRequest.getCleaningDuration() != 0 ? createCleaningActionRequest.getCleaningDuration() : structure.getCleaningDuration())
                    .build();

            return ResponseEntity.ok(cleaningActionRepository.save(newClean));

        } catch (Exception e) {
            logger.error("Error in createCleaningAction() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from createCleaningAction() method");
        }
    }

    @Override
    public ResponseEntity<CleaningAction> updateCleaningAction(Long cleaningActionId, UpdateCleaningActionRequest updateCleaningActionRequest) {
        try {
            logger.info("updateCleaningAction() called with CheckList: {}", updateCleaningActionRequest);
            CleaningAction cleaningActionToUpdate = cleaningActionRepository.findById(cleaningActionId).orElseThrow(() -> new RuntimeException("CleaningAction not found"));

            // Update the CleaningAction
            // Copy only not null properties from the request to the CleaningAction
            PropertiesHelper.copyNonNullProperties(updateCleaningActionRequest, cleaningActionToUpdate);

            if (updateCleaningActionRequest.getUserCleanerId() != null) {
                User userCleaner = userRepository.findById(updateCleaningActionRequest.getUserCleanerId()).orElseThrow(() -> new RuntimeException("User not found"));
                cleaningActionToUpdate.setUser(userCleaner);
            }

            if (updateCleaningActionRequest.getCheckListId() != null) {
                CheckList checkList = checkListRepository.findById(updateCleaningActionRequest.getCheckListId()).orElseThrow(() -> new RuntimeException("CheckList not found"));
                cleaningActionToUpdate.setCheckList(checkList);
            }

            return ResponseEntity.ok(cleaningActionRepository.save(cleaningActionToUpdate));

        } catch (Exception e) {
            logger.error("Error in updateCleaningAction() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from updateCleaningAction() method");
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteCleaningAction(Long cleaningActionId) {
        try {
            logger.info("deleteCleaningAction() called with id: {}", cleaningActionId);
            CleaningAction cleaningActionToDelete = cleaningActionRepository.findById(cleaningActionId).orElseThrow(() -> new RuntimeException("CleaningAction not found"));
            cleaningActionRepository.delete(cleaningActionToDelete);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in deleteCleaningAction() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from deleteCleaningAction() method");
        }
    }

    @Override
    public ResponseEntity<Boolean> addPictureToCleaningAction(Long cleaningActionId, MultipartFile file) {
        CleaningAction cleaningAction = cleaningActionRepository.findById(cleaningActionId).orElseThrow(() -> new RuntimeException("CleaningAction not found"));
        String message = "";
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            File savedFile = FileBuilder.builder()
                    .name(fileName)
                    .type(file.getContentType())
                    .imageData(file.getBytes())
                    .cleaningActions(cleaningAction)
                    .build();
            fileRepository.save(savedFile);

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            logger.error("Could not upload the file: " + file.getOriginalFilename() + "!");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @Override
    public ResponseEntity<Boolean> removePictureFromCleaningAction(Long pictureId) {
        File picture = fileRepository.findById(pictureId).orElseThrow(() -> new RuntimeException("Picture not found"));

        try {
            picture.setCleaningActions(null);

            fileRepository.delete(picture);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            logger.error("Could not delete the file: " + picture.getName() + "!");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getPicturesFromCleaningAction(Long cleaningActionId) {
        try {
            logger.debug("getPicturesFromCleaningAction() called with id: {}", cleaningActionId);
            CleaningAction cleaningAction = cleaningActionRepository.findById(cleaningActionId).orElseThrow(() -> new RuntimeException("CleaningAction not found"));

            List<ResponseFile> files = cleaningAction.getPictures().stream().map(dbFile -> {
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/files/")
                        .path(dbFile.getId().toString())
                        .toUriString();

                return new ResponseFile(
                        dbFile.getName(),
                        fileDownloadUri,
                        dbFile.getType(),
                        dbFile.getData().length);
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(files);
        } catch (Exception e) {
            logger.error("Could not get the files!");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Override
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
            CleaningAction cleaningAction = CleaningActionBuilder.builder()
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
