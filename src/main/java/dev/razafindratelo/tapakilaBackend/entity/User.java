package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class User implements UserDetails {
    @JsonProperty("email")
    private String email;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("profileImgPath")
    private String imgProfilePath;

    @JsonProperty("password")
    private String password;

    @JsonProperty("userRole")
    private UserRole userRole;

    @JsonProperty("userStatus")
    private boolean isActive;
    
    @JsonProperty("userCreatedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    private List<EventCategoryDetail> favoriteEventCategories;




    @Override
    public String toString() {
        return "User{" +
                "\n \t  email='" + email + '\'' +
                ",\n \t  lastName='" + lastName + '\'' +
                ",\n \t  firstName='" + firstName + '\'' +
                ",\n \t  imgProfilePath='" + imgProfilePath + '\'' +
                ",\n \t  password='" + password + '\'' +
                ",\n \t  userRole=" + userRole +
                ",\n \t  createdAt=" + createdAt +
                ",\n \t  isActive=" + isActive +
                ",\n \t  favoriteEventCategories=" + favoriteEventCategories +
                "\n }";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(
                        String.format("ROLE_%s", userRole.toString())
                )
        );
    }

    @Override
    public String getUsername() {
        return String.format("%s %s", lastName, firstName);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
