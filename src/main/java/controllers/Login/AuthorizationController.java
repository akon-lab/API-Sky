package controllers.Login;

import domain.Background.AccessToken;
import domain.Login.AdminAuth;
import domain.Login.PlayerAuth;
import domain.RealPerson.Administrator;
import domain.RealPerson.Player;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servises.auth.AdminAuthService;
import servises.auth.PlayerAuthService;
import servises.interfaces.IAuthorizationService;

@Path("auth")
public class AuthorizationController {
    private final IAuthorizationService<PlayerAuth> playerAuthService = new PlayerAuthService();
    private final IAuthorizationService<AdminAuth> adminAuthService = new AdminAuthService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AdminAuth data) {
        try {
            AccessToken token = adminAuthService.authenticateUser(data);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(PlayerAuth data) {
        try {
            AccessToken token = playerAuthService.authenticateUser(data);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
