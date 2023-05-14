package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.AccessLogFilter;
import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accessLogs")
@Tag(name = "AccessLogController", description = "The AccessLog APIs")
public interface AccessLogController {


    @Operation(summary = "filter", description = "Filter AccessLog", tags = {"AccessLog"})
    @PostMapping("/filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Error not found - EmptyArray Exception",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Page<AccessLog>> filter(
            @RequestBody(required = false) AccessLogFilter probe,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size,
            @RequestParam(required = false, name = "sortField") String sortField,
            @RequestParam(required = false, name = "sortDirection") String sortDirection) ;


    @Operation(summary = "Get AccessLog by id", description = "Get AccessLog by id", tags = {"AccessLog"})
    @GetMapping("structureFilter/{structureId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<List<AccessLogInfo>> getAccessLogByStructureId(@PathVariable @Schema(example = "1") Long structureId);

}
