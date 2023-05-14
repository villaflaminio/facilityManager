package it.bruffa.facilitymanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogFilter {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "1")
    private Long timestamp;
    @Schema(example = "1")
    private Long gateId;
    @Schema(example = "1")
    private Long userId;

}
