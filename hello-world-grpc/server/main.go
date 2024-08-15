package main

import (
	"context"
	"log"
	"net"

	pb "github.com/felipeazsantos/pluxxe/proto"
	"google.golang.org/grpc"
)

var addr = "0.0.0.0:50051"

type Server struct {
	pb.HelloWorldServiceServer
}

// // GetMessage returns the Hello World message
func (server *Server) GetMessage(ctx context.Context, in *pb.Empty) (*pb.HelloWorldMessage, error) {
	log.Print("GetMessage was invoked")
	return &pb.HelloWorldMessage{Message: "Hello World"}, nil
}

func main() {
	lis, err := net.Listen("tcp", addr)
	if err != nil {
		log.Fatalf("Failed to listen: %v\n", err)
	}
	log.Printf("Listing on: %s", addr)

	s := grpc.NewServer()
	pb.RegisterHelloWorldServiceServer(s, &Server{})

	if err = s.Serve(lis); err != nil {
		log.Fatalf("Failed to serve: %v\n", err)
	}
}
