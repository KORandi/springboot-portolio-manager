package com.example.portfolio.manager;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class PortfolioManagerApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String jwtSecret = dotenv.get("JWT_SECRET");
        String jwtExpiration = dotenv.get("JWT_EXPIRATION");

        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET is empty");
        }

        if (jwtExpiration == null || jwtExpiration.isEmpty()) {
            throw new IllegalStateException("JWT_EXPIRATION is empty");
        }

        System.setProperty("JWT_SECRET", Objects.requireNonNull(dotenv.get("JWT_SECRET")));
        System.setProperty("JWT_EXPIRATION", Objects.requireNonNull(dotenv.get("JWT_EXPIRATION")));

        SpringApplication.run(PortfolioManagerApplication.class, args);
    }
}