package servises.auth;

import domain.Background.AccessToken;
import domain.Login.PlayerAuth;
import domain.RealPerson.Administrator;
import domain.RealPerson.Player;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import repository.entities.PlayerRepository;
import servises.interfaces.IAuthorizationService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class PlayerAuthService implements IAuthorizationService<PlayerAuth> {

    private final PlayerRepository playerRepo = new PlayerRepository();

    public Player getByUsername(String issuer) {
        return playerRepo.getUserByUsername(issuer);
    }

    @Override
    public PlayerAuth signIn(PlayerAuth data) throws Exception {
        Player player = playerRepo.findUserByLogin(data);
        if (player == null) {
            throw new Exception("Authentication failrd!");
        }
        return new PlayerAuth(player.getUsername(), player.getPassword());

    }

    @Override
    public AccessToken authenticateUser(PlayerAuth data) throws Exception {
        PlayerAuth authenticatePlayer = signIn(data);
        return new AccessToken(issueToken(authenticatePlayer));
    }


    private String issueToken(PlayerAuth player) {// ???
        Instant now = Instant.now();
        String secretWord = "ChystayaArchitecturaUncleBackKEK";
        return Jwts
                .builder()
                .setIssuer(player.getUsername())
                .setIssuedAt(Date.from(now))
                .claim("1d50", new Random().nextInt(40) + 1)
                .setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .compact();
    }




}