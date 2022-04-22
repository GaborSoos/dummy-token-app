package com.example.springsecuritydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private PlayerAuthService playerAuthService;
    private JWTTokenService jwtTokenService;

    @Autowired
    public LoginController(PlayerAuthService playerAuthService,
                           JWTTokenService jwtTokenService) {
        this.playerAuthService = playerAuthService;
        this.jwtTokenService = jwtTokenService;
    }

    @RequestMapping("/player")
    public String welcomeAfterLogin(UsernamePasswordAuthenticationToken user) {
        PlayerDto playerDto = (PlayerDto)user.getPrincipal();
        return "Hi, " + playerDto.getUsername() +
               " from " + playerDto.getKingdomName() +
               " (kingdomId: " + playerDto.getKingdomId() +
               ")!";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PlayerAuthDto playerAuthDto) {
        playerAuthService.validateLoginInput(playerAuthDto);
        PlayerDto playerDto = playerAuthService.authenticate(playerAuthDto);
        //TODO: body
        return ResponseEntity.status(200)
                .header(SecurityConstants.JWT_HEADER, jwtTokenService.generateJWTToken(playerDto))
                .body(null);
        }

    }
