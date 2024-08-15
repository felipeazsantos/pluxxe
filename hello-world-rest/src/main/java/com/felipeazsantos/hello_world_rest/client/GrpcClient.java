package com.felipeazsantos.hello_world_rest.client;

import helloworld.HelloWorldServiceGrpc;
import helloworld.Helloworld.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {

    private final HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub;
    private final String serverHost = "0.0.0.0";
    private final int serverPort = 50051;

    public GrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        stub = HelloWorldServiceGrpc.newBlockingStub(channel);
    }

    public String getHelloWorldMessage() {
        HelloWorldMessage response = stub.getMessage(Empty.newBuilder().build());
        return response.getMessage();
    }
}
