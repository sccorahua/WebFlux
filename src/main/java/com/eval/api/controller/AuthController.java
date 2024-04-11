package com.eval.api.controller;

import com.eval.api.entity.AuthRequest;
import com.eval.api.entity.AuthResponse;
import com.eval.api.entity.User;
import com.eval.api.security.JwtTokenUtil;
import com.eval.api.service.impl.jwtUserDetailsService;
import com.eval.api.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(("/login"))
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private jwtUserDetailsService userDetailsService;

    @Autowired
    private userService usService;

    @PostMapping
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request){
        return Mono.fromCallable(() -> {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        }).onErrorResume(ex -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @PostMapping("/registrar")
    public Mono<User> registrarUser(@RequestBody User us){
        Mono<User> use = usService.registrarUser(us);
        return use;
    }
}
