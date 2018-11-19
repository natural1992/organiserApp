package pl.wojciechbury.organiser.models.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashingService {
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String hash(String password){
        return getPasswordEncoder().encode(password);
    }

    public boolean match(String hashedPassword, String input){
        return getPasswordEncoder().matches(input, hashedPassword);
    }
}
