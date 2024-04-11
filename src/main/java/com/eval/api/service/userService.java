package com.eval.api.service;

import com.eval.api.entity.User;
import reactor.core.publisher.Mono;

public interface userService {
    public Mono<User> registrarUser(User us);
}
