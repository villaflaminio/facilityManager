package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.builder.FileBuilder;
import it.bruffa.facilitymanager.model.builder.MaintenanceBuilder;
import it.bruffa.facilitymanager.model.builder.QuoteBuilder;
import it.bruffa.facilitymanager.model.dto.MaintenanceFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.bruffa.facilitymanager.model.dto.request.UpdateMaintenanceRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.MaintenanceService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CheckListRepository checkListRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FileRepository fileRepository;
    private static final Logger logger = LoggerFactory.getLogger(MaintenanceServiceImpl.class);

    @Override
    public ResponseEntity<Page<Maintenance>> filter(MaintenanceFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        try {
            logger.debug("Enter into filter method with probe: {}", probe);
            Pageable pageable;
            Maintenance filter = new Maintenance();

            if (probe.getStructureId() != null) {
                if (structureRepository.existsById(probe.getStructureId())) {
                    Structure structure = structureRepository.findById(probe.getStructureId()).get();
                    structure.setGate(null);
                    structure.setQuote(null);
                    filter.setStructure(structure);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

            if (probe.getUserId() != null) {
                if (userRepository.existsById(probe.getUserId())) {
                    filter.setUser(userRepository.findById(probe.getUserId()).get());

                } else {
                    return ResponseEntity.notFound().build();
                }
            }


            if (probe.getCheckListId() != null) {
                if (checkListRepository.existsById(probe.getCheckListId())) {
                    filter.setCheckList(checkListRepository.findById(probe.getCheckListId()).get());

                } else {
                    return ResponseEntity.notFound().build();
                }
            }


            if (probe.getQuoteId() != null) {
                if (quoteRepository.existsById(probe.getQuoteId())) {
                    filter.setQuote(quoteRepository.findById(probe.getQuoteId()).get());

                } else {
                    return ResponseEntity.notFound().build();
                }
            }


            if (probe.getFeedbackId() != null) {
                if (feedbackRepository.existsById(probe.getFeedbackId())) {
                    filter.setFeedback(feedbackRepository.findById(probe.getFeedbackId()).get());

                } else {
                    return ResponseEntity.notFound().build();
                }
            }

            PropertiesHelper.copyNonNullProperties(probe, filter);

            if (org.springframework.util.StringUtils.isEmpty(sortField)) {
                pageable = PageRequest.of(page, size);
            } else {

                Sort.Direction dir = StringUtils.isEmpty(sortDirection) ? Sort.Direction.ASC : Sort.Direction.valueOf(sortDirection.trim().toUpperCase());
                pageable = PageRequest.of(page, size, dir, sortField);
            }

            ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            Example<Maintenance> example = Example.of(filter, matcher);

            return ResponseEntity.ok(maintenanceRepository.findAll(example, pageable));
        } catch (Exception e) {
            logger.error("Error in filter method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Maintenance> getMaintenanceById(Long maintenanceId) {
        try {
            logger.debug("Enter into getMaintenanceById method with id: {}", maintenanceId);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            logger.debug("Maintenance found: {}", maintenance);
            return ResponseEntity.ok(maintenance);
        } catch (Exception e) {
            logger.error("Error in getMaintenanceById method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Maintenance> createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        try {
            logger.debug("Enter into createMaintenance method with request: {}", createMaintenanceRequest);
            Structure structure = structureRepository.findById(createMaintenanceRequest.getStructureId()).orElseThrow(() -> new Exception("Structure not found"));
            User user = userRepository.findById(createMaintenanceRequest.getUserId()).orElseThrow(() -> new Exception("User not found"));
            CheckList checkList = checkListRepository.findById(createMaintenanceRequest.getCheckListId()).orElseThrow(() -> new Exception("CheckList not found"));

            Maintenance maintenance = MaintenanceBuilder.builder()
                    .structure(structure)
                    .user(user)
                    .checkList(checkList)
                    .description(createMaintenanceRequest.getDescription())
                    .date(createMaintenanceRequest.getDate())
                    .status(MaintenanceStatus.CREATED)
                    .build();

            Quote quote = QuoteBuilder.builder()
                    .maintenance(maintenance)
                    .description(createMaintenanceRequest.getDescription())
                    .structure(structure)
                    .user(user)
                    .accepted(false)
                    .build();
            maintenanceRepository.save(maintenance);
            quoteRepository.save(quote);

            logger.debug("Maintenance created: {}", maintenance);
            return ResponseEntity.ok(maintenance);
        } catch (Exception e) {
            logger.error("Error in createMaintenance method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, UpdateMaintenanceRequest modifyMaintenanceRequest) {
        try {
            logger.debug("Enter into updateMaintenance method with id: {} and request: {}", maintenanceId, modifyMaintenanceRequest);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            PropertiesHelper.copyNonNullProperties(modifyMaintenanceRequest, maintenance);

            maintenanceRepository.save(maintenance);
            logger.debug("Maintenance updated: {}", maintenance);
            return ResponseEntity.ok(maintenance);
        } catch (Exception e) {
            logger.error("Error in updateMaintenance method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteMaintenance(Long maintenanceId) {
        try {
            logger.debug("Enter into deleteMaintenance method with id: {}", maintenanceId);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            maintenanceRepository.delete(maintenance);
            logger.debug("Maintenance deleted: {}", maintenance);
            return ResponseEntity.ok(true);


        } catch (Exception e) {
            logger.error("Error in deleteMaintenance method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> addPicture(Long maintenanceId, MultipartFile file) {
        try {
            logger.debug("Enter into addPicture method with id: {} and file: {}", maintenanceId, file);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            String message = "";
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                File savedFile = FileBuilder.builder()
                        .name(fileName)
                        .type(file.getContentType())
                        .imageData(file.getBytes())
                        .maintenance(maintenance)
                        .build();
                fileRepository.save(savedFile);

                maintenance.getPictures().add(savedFile);
                maintenanceRepository.save(maintenance);

                return ResponseEntity.status(HttpStatus.OK).body(true);
            } catch (Exception e) {
                logger.error("Could not upload the file: " + file.getOriginalFilename() + "!");
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
        } catch (Exception e) {
            logger.error("Error in addPicture method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> deletePicture(Long pictureId) {
        try {
            logger.debug("Enter into deletePicture method with id: {}", pictureId);
            File file = fileRepository.findById(pictureId).orElseThrow(() -> new Exception("File not found"));
            fileRepository.delete(file);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in deletePicture method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    public ResponseEntity<List<ResponseFile>> getPictures(Long maintenanceId) {
        try {
            logger.debug("Enter into getPicture method with id: {}", maintenanceId);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            List<ResponseFile> files = maintenance.getPictures().stream().map(dbFile -> {
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
            logger.error("Error in getPicture method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> addDocument(Long maintenanceId, MultipartFile file) {
        try {
            logger.debug("Enter into addDocument method with id: {} and file: {}", maintenanceId, file);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            String message = "";
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                File savedFile = FileBuilder.builder()
                        .name(fileName)
                        .type(file.getContentType())
                        .imageData(file.getBytes())
                        .maintenance(maintenance)
                        .build();
                fileRepository.save(savedFile);

                maintenance.getDocuments().add(savedFile);
                maintenanceRepository.save(maintenance);

                return ResponseEntity.status(HttpStatus.OK).body(true);
            } catch (Exception e) {
                logger.error("Could not upload the file: " + file.getOriginalFilename() + "!");
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
        } catch (Exception e) {
            logger.error("Error in addDocument method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getDocumentS(Long maintenanceId) {
        try {
            logger.debug("Enter into getDocumentS method with id: {}", maintenanceId);
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new Exception("Maintenance not found"));
            List<ResponseFile> files = maintenance.getDocuments().stream().map(dbFile -> {
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
            logger.error("Error in getPicture method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteDocument(Long documentId) throws Exception {
        try {
            logger.debug("Enter into deleteDocument method with id: {}", documentId);
            File file = fileRepository.findById(documentId).orElseThrow(() -> new Exception("File not found"));
            fileRepository.delete(file);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in deleteDocument method: {}", e.getMessage());
            throw e;
        }
    }


}
