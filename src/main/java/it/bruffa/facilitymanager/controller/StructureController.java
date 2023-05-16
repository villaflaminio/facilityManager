package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.StructureFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


public interface StructureController {

    @Operation(summary = "filter", description = "Filter structure", tags = {"structure"})
    @PostMapping("/filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Error not found - EmptyArray Exception",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Page<Structure>> filter(
            @RequestBody(required = false) StructureFilter probe,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size,
            @RequestParam(required = false, name = "sortField") String sortField,
            @RequestParam(required = false, name = "sortDirection") String sortDirection);

    @Operation(summary = "Get structure by id", description = "Get structure by id", tags = {"structure"})
    @GetMapping("/{structureId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<StructureInfo> getStructureById(@PathVariable @Schema(example = "1") Long structureId) throws ItemNotFoundException;

    //get all structures little info
    @Operation(summary = "Get all structures info", description = "Get all structures - only name and id", tags = {"structure"})
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Error not found - EmptyArray Exception",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<List<StructureIdInfo>> getStructuresList() throws ItemNotFoundException;


    @Operation(summary = "getReservationsBetweenDatesAndStructure", description = "Get reservations", tags = {"structure"})
    @GetMapping("/{structureId}/busyDates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<List<Reservation>> getReservationsBetweenDatesAndStructure(@RequestParam @Schema(example = "2021-05-11") LocalDate startDate,@RequestParam @Schema(example = "2023-05-11") LocalDate endDate, @PathVariable @Schema(example = "1") Long structureId);

    @Operation(summary = "Create structure", description = "Create structure", tags = {"structure"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Structure> createStructure(@RequestBody @Valid CreateStructureRequest createStructureRequest) throws Exception;


    @Operation(summary = "Update structure", description = "Update structure", tags = {"structure"})
    @PutMapping("/{structureId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Structure> updateStructure(@PathVariable @Schema(example = "1") Long structureId, @RequestBody @Valid CreateStructureRequest structureRequest) throws ItemNotFoundException, Exception;

    @Operation(summary = "Delete structure", description = "Delete structure", tags = {"structure"})
    @DeleteMapping("/{structureId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteStructure(@PathVariable @Schema(example = "1") Long structureId) throws ItemNotFoundException;


}
