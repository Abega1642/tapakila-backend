package dev.razafindratelo.tapakilaBackend.config;

import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.exception.ActionNotAllowedException;
import dev.razafindratelo.tapakilaBackend.service.jwtService.TokenService;
import dev.razafindratelo.tapakilaBackend.service.userService.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Getter
public class TokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserService userService;
    private final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();

	private static final List<String> EXCLUDED_PATHS = List.of(
            "/user/sign-in",
			"/user/sign-up",
			"/user/activate-account",
			"/user/update-password",
			"/ping-pong",
            "/events",
            "/event",
            "/events/filter",
            "/time-zones",
            "/currencies",
            "/events-status",
            "/user-roles",
			"/event-types",
			"/users/total-count",
            "/events/total-count",
            "/ticket-types",
            "/event-categories",
            "/event-types-enum",
			"/qr-codes",
			"/tickets/",
			"/buy-tickets",
			"/tickets"
	);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

		String path = request.getServletPath();
    
		if (EXCLUDED_PATHS.stream().anyMatch(path::startsWith)) {
		    filterChain.doFilter(request, response);
		    return;
		}

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.length() < 10)
            throw new ActionNotAllowedException("No authorization found or authorization header format not valid");

        String accessTokenValue = authHeader.replace("Bearer ", "");
        AccessToken correspondingAccessToken = tokenService.findByValue(accessTokenValue);

        if (!correspondingAccessToken.isValid() || correspondingAccessToken.getExpiresAt().isBefore(DEFAULT_DATE_TIME))
            throw new ActionNotAllowedException("Access token has expired.");

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            User correspondingUser = userService.findByEmail(correspondingAccessToken.getUserEmail());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    correspondingUser,
                    null,
                    correspondingUser.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
