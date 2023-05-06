package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessLogs")
@Tag(name = "AccessLogController", description = "The AccessLog APIs")
public interface AccessLogController {
}
