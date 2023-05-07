package it.bruffa.facilitymanager.model.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.entity.Structure;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGateRequest {

    @NotNull
    @Schema(description = "Name of the gate", example = "Gate 1")
    private String name;
    @NotNull
    @Schema(description = "Structure ID  of the gate", example = "1")
    private Long structureId;

    @NotNull
    @Schema(description = "Description of the gate", example = "Gate 1 - Main entrance")
    private String description;

    @NotNull
    @Schema(description = "Is the gate active?", example = "true")
    private Boolean isActive;

    @NotNull
    @Schema(description = "MQTT topic of the gate", example = "facilityManager/Opengate/gate/")
    private String mqttTopic;
}
