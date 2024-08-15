package com.felipeazsantos.hello_world_rest.controller;

import com.felipeazsantos.hello_world_rest.client.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    private final GrpcClient grpcClient;

    public HelloWorldController(GrpcClient grpcClient) {
        this.grpcClient = grpcClient;
    }

    @GetMapping("/helloWorld")
    public ResponseEntity<Map<String, String>> getHelloWorldMessage() {
        String message = grpcClient.getHelloWorldMessage();
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}
