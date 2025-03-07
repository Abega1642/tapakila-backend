package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class User implements UserDetails {
    private String email;
    private String lastName;
    private String firstName;
    private String imgProfilePath;
    private String password;
    private UserRole userRole;
    private boolean isActive;
    private List<EventCategoryDetail> favoriteEventCategories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(
                        String.format("ROLE_%s",userRole.toString())
                )
        );
    }

    @Override
    public String getUsername() {
        return String.format("%s %s", lastName, firstName);
    }


    @Override
    public String toString() {
        return "User{" +
                "\n \t  email='" + email + '\'' +
                ",\n \t  lastName='" + lastName + '\'' +
                ",\n \t  firstName='" + firstName + '\'' +
                ",\n \t  imgProfilePath='" + imgProfilePath + '\'' +
                ",\n \t  password='" + password + '\'' +
                ",\n \t  userRole=" + userRole +
                ",\n \t  isActive=" + isActive +
                ",\n \t  favoriteEventCategories=" + favoriteEventCategories +
                "\n }";
    }
}
