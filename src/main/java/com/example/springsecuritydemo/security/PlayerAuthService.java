package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.security.haveto.Player;
import com.example.springsecuritydemo.security.haveto.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: TribesAuthService
@Service
public class PlayerAuthService {
    /*Scenario 1:
    Given the POST /login endpoint.
    When I send request to it with a username.
    Then I get an HTTP 400 error with message: "Password is required."
    Scenario 2:
    Given the POST /login endpoint.
    When I send request to it with a password.
    Then I get an HTTP 400 error with message: "Username is required."
    Scenario 3:
    Given the POST /login endpoint.
    When I send an empty request to it.
    Then I get an HTTP 400 error with message: "All fields are required."
    Scenario 4:
    Given the POST /login endpoint.
    When I send a request to it with wrong username or password.
    Then I get an HTTP 401 error with message: "Username or password is incorrect."
    Scenario 5:
    Given the POST /login endpoint.
    When I send a request to it with a good username and password.
    Then I get back and HTTP 200 response with a JWT token.*/


    private PlayerRepository playerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerAuthService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void validateLoginInput(PlayerAuthDto playerAuthDto) {
        if(playerAuthDto == null ||
            ((playerAuthDto.getUsername() == null || playerAuthDto.getUsername().isEmpty()) &&
            (playerAuthDto.getPassword() == null || playerAuthDto.getPassword().isEmpty()))) {
            throw new LoginValidationException("All fields are required.", 0);
        }
        if(playerAuthDto.getUsername() == null || playerAuthDto.getUsername().isEmpty()) {
            throw new LoginValidationException("Username is required.", 0);
        }
        if(playerAuthDto.getPassword() == null || playerAuthDto.getPassword().isEmpty()) {
            throw new LoginValidationException("Password is required.", 0);
        }
    }

    public PlayerDto authenticate(PlayerAuthDto playerAuthDto) throws AuthenticationException {
        String username = playerAuthDto.getUsername();
        String pwd = playerAuthDto.getPassword();
        List<Player> players = playerRepository.findByUsername(username);
        if(players.size() > 0) {
            if(passwordEncoder.matches(pwd, players.get(0).getPassword())) {
                return convert(players.get(0));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    private PlayerDto convert(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getUsername(),
                player.getKingdom().getId(),
                player.getKingdom().getName(),
                player.getAvatar(),
                player.getPoints()
        );
    }
}
