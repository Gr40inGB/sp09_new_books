package org.gr40in.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Sp09NewBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp09NewBooksApplication.class, args);
    }

}
