package com.eval.api.service.impl;

import com.eval.api.entity.User;
import com.eval.api.repository.UserRepository;
import com.eval.api.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Mono<User> registrarUser(User us) {
        User use = userRepository.save(us);
        return Mono.just(use);
    }

}
