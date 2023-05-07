package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkLists")
@Tag(name = "CheckListController", description = "The CheckList APIs")
public interface CheckListController {

    @Operation(summary = "Get checkList by id", description = "Get checkList by id", tags = {"checkList"})
    @GetMapping("/{checkListId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CheckList> getCheckListById(@PathVariable @Schema(example = "1") Long checkListId) throws ItemNotFoundException;

    @Operation(summary = "Get checkList by name", description = "Get checkList by name", tags = {"checkList"})
    @GetMapping("/name/{checkListName}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CheckList> getCheckListByName(@PathVariable @Schema(example = "name") String checkListName) throws ItemNotFoundException;

    @Operation(summary = "Create checkList", description = "Create checkList", tags = {"checkList"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CheckList> createCheckList(@RequestBody @Valid CreateCheckListRequest createCheckListRequest) throws Exception;

    @Operation(summary = "Update checkList", description = "Update checkList", tags = {"checkList"})
    @PutMapping("/{checkListId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<CheckList> updateCheckList(@PathVariable @Schema(example = "1") Long checkListId, @RequestBody @Valid CreateCheckListRequest createCheckListRequest) throws ItemNotFoundException, Exception;

    @Operation(summary = "Delete checkList", description = "Delete checkList", tags = {"checkList"})
    @DeleteMapping("/{checkListId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteCheckList(@PathVariable @Schema(example = "1") Long checkListId) throws ItemNotFoundException;
}
