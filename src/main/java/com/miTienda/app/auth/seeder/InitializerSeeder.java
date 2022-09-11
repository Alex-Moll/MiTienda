package com.miTienda.app.auth.seeder;

import com.miTienda.app.model.request.UserRequest;
import com.miTienda.app.repository.UserRepository;
import com.miTienda.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.stream.IntStream;

@Component
public class InitializerSeeder implements CommandLineRunner {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            this.createUsers(2, "admin");
            this.createUsers(2, "user");
        }

    }

    private void createUsers(int users, String userType) {
        final String PASSWORD = "1234";
        if (userType.equalsIgnoreCase("admin")){
            IntStream.range(0, users).forEach(userNumber ->
                    createAdmin(userType, PASSWORD, userNumber));
        }
        else{
            IntStream.range(0, users).forEach(userNumber ->
                    createNormalUser(userType, PASSWORD, userNumber));
        }
    }

    private void createNormalUser(String userType, String PASSWORD, Integer userNumber) {
        try {
            authService.register(new UserRequest(userType + userNumber, userType + userNumber,
                    "email"+ userType + userNumber +"@mail.com", PASSWORD, userType +"Photo"+ userNumber +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createAdmin(String userType, String PASSWORD, Integer userNumber) {
        try {
            authService.registerAdmin(new UserRequest(userType + userNumber, userType + userNumber,
                    "email"+ userType + userNumber +"@mail.com", PASSWORD, userType +"Photo"+ userNumber +".png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
