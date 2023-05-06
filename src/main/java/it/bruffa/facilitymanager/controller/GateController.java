package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gates")
@Tag(name = "GateController", description = "The gates APIs")
public interface GateController {
}
