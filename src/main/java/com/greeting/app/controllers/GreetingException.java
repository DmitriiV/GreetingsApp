package com.greeting.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Greeting caused an error.")
public class GreetingException extends RuntimeException{
}
