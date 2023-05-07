package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.CleaningActionFilter;
import it.bruffa.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.bruffa.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.CleaningActionInfo;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cleaningActions")
@Tag(name = "CleaningActionController", description = "The cleaningActions APIs")
public interface CleaningActionController {
    @Operation(summary = "filter", description = "Filter cleaningAction", tags = {"cleaningAction"})
    @PostMapping("/filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Error not found - EmptyArray Exception",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Page<CleaningAction>> filter(
            @RequestBody(required = false) CleaningActionFilter probe,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size,
            @RequestParam(required = false, name = "sortField") String sortField,
            @RequestParam(required = false, name = "sortDirection") String sortDirection);

    @Operation(summary = "Get cleaningAction by id", description = "Get cleaningAction by id", tags = {"cleaningAction"})
    @GetMapping("/{cleaningActionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CleaningActionInfo> getCleaningActionById(@PathVariable @Schema(example = "1") Long cleaningActionId) throws ItemNotFoundException;

    @Operation(summary = "Create cleaningAction", description = "Create cleaningAction", tags = {"cleaningAction"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CleaningAction> createCleaningAction(@RequestBody @Valid CreateCleaningActionRequest createCleaningActionRequest) throws Exception;

    @Operation(summary = "Update cleaningAction", description = "Update cleaningAction", tags = {"cleaningAction"})
    @PutMapping("/{cleaningActionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CleaningAction> updateCleaningAction(@PathVariable @Schema(example = "1") Long cleaningActionId, @RequestBody @Valid UpdateCleaningActionRequest updateCleaningActionRequest) throws ItemNotFoundException, Exception;

    @Operation(summary = "Delete cleaningAction", description = "Delete cleaningAction", tags = {"cleaningAction"})
    @DeleteMapping("/{cleaningActionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteCleaningAction(@PathVariable @Schema(example = "1") Long cleaningActionId) throws ItemNotFoundException;


    @Operation(summary = "Add picture to cleaningAction", description = "Add picture to cleaningAction", tags = {"cleaningAction"})
    @PostMapping("/{cleaningActionId}/picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> addPictureToCleaningAction(@PathVariable @Schema(example = "1") Long cleaningActionId, @RequestParam("file") MultipartFile file) throws ItemNotFoundException;


    @Operation(summary = "Remove picture from cleaningAction", description = "Remove picture from cleaningAction", tags = {"cleaningAction"})
    @DeleteMapping("/picture/{pictureId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> removePictureFromCleaningAction( @PathVariable @Schema(example = "1") Long pictureId) throws ItemNotFoundException;


    //get pictures from cleaningAction
    @Operation(summary = "Get pictures from cleaningAction", description = "Get pictures from cleaningAction", tags = {"cleaningAction"})
    @GetMapping("/{cleaningActionId}/pictures")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<List<ResponseFile>> getPicturesFromCleaningAction(@PathVariable @Schema(example = "1") Long cleaningActionId) throws ItemNotFoundException;
}
