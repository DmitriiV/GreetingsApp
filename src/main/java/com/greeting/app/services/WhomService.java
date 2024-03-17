package com.greeting.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhomService {
    @Value("${whom.service.default.name}")
    private String defaultName;
    public final String whom(final Optional<String> name) {
        return name.orElse(this.defaultName);
    }
}
