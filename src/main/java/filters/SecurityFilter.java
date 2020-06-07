package filters;

import domain.Login.AdminAuth;
import domain.Login.PlayerAuth;
import domain.RealPerson.Administrator;
import domain.RealPerson.Player;
import filters.customAnnotations.JWTTokenNeeded;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import servises.auth.AdminAuthService;
import servises.auth.PlayerAuthService;
import servises.interfaces.IAuthorizationService;

import javax.annotation.Priority;
import java.io.IOException;
import java.security.Principal;

@JWTTokenNeeded
@Provider
@Priority(Priorities.AUTHORIZATION)
public class SecurityFilter {
    private final PlayerAuthService playerAuthServ = new PlayerAuthService();
    private final AdminAuthService adminAuthServ = new AdminAuthService();

    private static final String AUTH_SCHEME = "Bearer";

    //multi
    private void abortWithUnauth(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private boolean isTokenBasedAuth(String adminAuthHeader) {
        return adminAuthHeader != null && adminAuthHeader.toLowerCase().startsWith(AUTH_SCHEME.toLowerCase());
    }

    //admin
    public void filterAdmin(ContainerRequestContext requestContext) throws IOException {
        String adminAuthHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isTokenBasedAuth(adminAuthHeader)) {
            abortWithUnauth(requestContext);
        }
        String token = adminAuthHeader.substring(AUTH_SCHEME.length()).trim();

        try {
            final Administrator admin = validateTokenAdmin(token);

            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return (Principal) admin::getIdentityName;
                }

                @Override
                public boolean isUserInRole(String s) {
                    return (admin.getRang() != null && admin.getRang().equals(s));
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return null;
                }
            });
        } catch (Exception e) {
            abortWithUnauth(requestContext);
        }
    }

    private Administrator validateTokenAdmin(String token) {
        String secretWord = "OdnazhdyDavnymDavnoZhylyBylyConec";
        Jws<Claims> result = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .build().parseClaimsJws(token);

        return adminAuthServ.getByUsername(result.getBody().getIssuer());
    }


    //player
    public void filterPlayer(ContainerRequestContext requestContext) throws IOException {
        String playerAuthHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isTokenBasedAuth(playerAuthHeader)) {
            abortWithUnauth(requestContext);
        }
        String token = playerAuthHeader.substring(AUTH_SCHEME.length()).trim();

        try {
            final Player player = validateTokenPlayer(token);

            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return (Principal) player::getUsername;
                }

                @Override
                public boolean isUserInRole(String s) {
                    return false;
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return null;
                }
            });
        } catch (Exception e) {
            abortWithUnauth(requestContext);
        }
    }

    private Player validateTokenPlayer(String token) {
        String secretWord = "OdnazhdyDavnymDavnoZhylyBylyConec";
        Jws<Claims> result = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .build().parseClaimsJws(token);

        return playerAuthServ.getByUsername(result.getBody().getIssuer());
    }


}
