package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class User {
    private String email;
    private String lastName;
    private String firstName;
    private String password;
    private UserRole userRole;
    private boolean status;

    @Override
    public String toString() {
        return "User{" +
                "\n \t  email='" + email + '\'' +
                ",\n \t  lastName='" + lastName + '\'' +
                ",\n \t  firstName='" + firstName + '\'' +
                ",\n \t  password='" + password + '\'' +
                ",\n \t  userRole=" + userRole +
                ",\n \t  status=" + status +
                ",\n \t  favoriteEventCategories=" + favoriteEventCategories +
                "\n }";
    }

    private List<EventCategory> favoriteEventCategories;
}
