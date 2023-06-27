package it.flaminiovilla.facilitymanager.model.dto;

import it.flaminiovilla.facilitymanager.model.entity.Role;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class AuthResponseDTO {

    private Long id;
    private String email;
    private String name;
    private Collection<Role> role;
    private String token;
    private String refreshToken;
    private int duration;

}
