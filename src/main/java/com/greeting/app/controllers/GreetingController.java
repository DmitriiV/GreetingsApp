package com.greeting.app.controllers;

import com.greeting.app.core.Greeting;
import com.greeting.app.services.WhenService;
import com.greeting.app.services.WhomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class GreetingController {

    @Autowired
    private WhomService greetingsService;
    @Autowired
    private WhenService timeService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @GetMapping("greet")
    public ResponseEntity<Greeting> greet(@RequestParam final Optional<String> name) {
        try {
            final var whom = taskExecutor.submit(() -> greetingsService.whom(name));
            final var when = taskExecutor.submit(() -> timeService.when());
            return new ResponseEntity<>(new Greeting(whom.get(), when.get()), HttpStatus.OK);
        } catch (Exception e) {
            throw new GreetingException();
        }
    }
}
