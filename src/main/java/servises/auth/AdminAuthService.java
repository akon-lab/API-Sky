package servises.auth;

import domain.Background.AccessToken;
import domain.Login.AdminAuth;
import domain.RealPerson.Administrator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import repository.entities.AdminRepository;
import servises.interfaces.IAuthorizationService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class AdminAuthService implements IAuthorizationService<AdminAuth> {
    private final AdminRepository adminRepo = new AdminRepository();

    public Administrator getByUsername(String issuer) {
        return adminRepo.getUserByIdentityName(issuer);
    }

    @Override
    public AdminAuth signIn(AdminAuth data) throws Exception {
        Administrator admin = adminRepo.findUserByLogin(data);
        if (admin == null) {
            throw new Exception("Authentication failed!");
        }
        return new AdminAuth(admin.getIdentityName(),admin.getPassword());
    }


    @Override
    public AccessToken authenticateUser(AdminAuth data) throws Exception {

        AdminAuth authAdmin = signIn(data);
        return new AccessToken(issueToken(authAdmin));
    }



    private String issueToken(AdminAuth authAdmin) {//?????
        Instant now =Instant.now();
        String secretWord = "ChystayaKekChuberekRuskyePoymutArchitecturaUncleBackKEK";
        return Jwts
                .builder()
                .setIssuer(authAdmin.getUsername())
                .setIssuedAt(Date.from(now))
                .claim("1d20",new Random().nextInt(30)+1)
                .setExpiration(Date.from(now.plus(8, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .compact();
    }
}
