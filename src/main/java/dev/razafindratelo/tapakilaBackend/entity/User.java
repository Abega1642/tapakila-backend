package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class User {
    private String email;
    private String lastName;
    private String firstName;
    private String password;
    private UserRole userRole;
    private boolean isDisabled;
}
