package it.flaminiovilla.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.flaminiovilla.facilitymanager.model.dto.MaintenanceFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.flaminiovilla.facilitymanager.model.dto.request.UpdateMaintenanceRequest;
import it.flaminiovilla.facilitymanager.model.entity.Maintenance;
import it.flaminiovilla.facilitymanager.model.exception.ApiError;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.utilities.ResponseFile;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface MaintenanceController {

    @Operation(summary = "filter", description = "Filter Maintenance", tags = {"maintenance"})
    @PostMapping("/filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Error not found - EmptyArray Exception",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Page<Maintenance>> filter(
            @RequestBody(required = false) MaintenanceFilter probe,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size,
            @RequestParam(required = false, name = "sortField") String sortField,
            @RequestParam(required = false, name = "sortDirection") String sortDirection);


    //get by id
    @Operation(summary = "Get maintenance by id", description = "Get maintenance by id", tags = {"maintenance"})
    @GetMapping("/{maintenanceId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Maintenance> getMaintenanceById(@PathVariable @Schema(example = "1") Long maintenanceId) throws ItemNotFoundException;


    //create
    @Operation(summary = "Create maintenance", description = "Create maintenance", tags = {"maintenance"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request - The request was invalid or cannot be served",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Maintenance> createMaintenance(@RequestBody @Valid CreateMaintenanceRequest createMaintenanceRequest);

    @Operation(summary = "Update maintenance", description = "Update maintenance", tags = {"maintenance"})
    @PutMapping("/{maintenanceId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Maintenance> updateMaintenance(@PathVariable @Schema(example = "1") Long maintenanceId, @RequestBody @Valid UpdateMaintenanceRequest modifyMaintenanceRequest) throws ItemNotFoundException;

    @Operation(summary = "Delete maintenance", description = "Delete maintenance", tags = {"maintenance"})
    @DeleteMapping("/{maintenanceId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteMaintenance(@PathVariable @Schema(example = "1") Long maintenanceId) throws ItemNotFoundException;

    @Operation(summary = "Add picture to maintenance", description = "Add picture to maintenance", tags = {"maintenance"})
    @PostMapping("/{maintenanceId}/picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> addPicture(@PathVariable @Schema(example = "1") Long maintenanceId, @RequestParam("file") MultipartFile file) throws ItemNotFoundException;


    @Operation(summary = "Delete picture from maintenance", description = "Delete picture from maintenance", tags = {"maintenance"})
    @DeleteMapping("/{pictureId}/picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deletePicture(@PathVariable @Schema(example = "1") Long pictureId) throws ItemNotFoundException;

    @Operation(summary = "Get picture from maintenance", description = "Get picture from maintenance", tags = {"maintenance"})
    @GetMapping("/{maintenanceId}/picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<List<ResponseFile>> getPictures(@PathVariable @Schema(example = "1") Long maintenanceId) throws ItemNotFoundException;

    @Operation(summary = "Add document to maintenance", description = "Add document to maintenance", tags = {"maintenance"})
    @PostMapping("/{maintenanceId}/document")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> addDocument(@PathVariable @Schema(example = "1") Long maintenanceId, @RequestParam("file") MultipartFile file) throws ItemNotFoundException;


    @Operation(summary = "Get document from maintenance", description = "Get document from maintenance", tags = {"maintenance"})
    @GetMapping("/{maintenanceId}/document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<List<ResponseFile>> getDocuments(@PathVariable @Schema(example = "1") Long maintenanceId) throws ItemNotFoundException;

    @Operation(summary = "Delete document from maintenance", description = "Delete document from maintenance", tags = {"maintenance"})
    @DeleteMapping("/{documentId}/document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteDocument(@PathVariable @Schema(example = "1") Long documentId) throws Exception;
}
