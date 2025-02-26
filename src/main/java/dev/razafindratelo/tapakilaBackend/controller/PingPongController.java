package dev.razafindratelo.tapakilaBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/ping-pong")
public class PingPongController {

    @GetMapping
    public String pingPong() {
        return "<h1> Pong-Pong controller </h1>";
    }

}
