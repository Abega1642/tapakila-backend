package dev.razafindratelo.tapakilaBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("ping-pong")
    public String pingPong() {
        return "Ping-Pong controller";
    }

}
