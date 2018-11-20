package pl.wojciechbury.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.wojciechbury.organiser.models.entities.UserEntity;
import pl.wojciechbury.organiser.models.forms.RegisterForm;
import pl.wojciechbury.organiser.models.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    final UserRepository userRepository;
    final PasswordHashingService passwordHashingService;

    @Autowired
    public ApiService(UserRepository userRepository, PasswordHashingService passwordHashingService){
        this.userRepository = userRepository;
        this.passwordHashingService = passwordHashingService;
    }

    public ResponseEntity getUserById(int id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){

            return ResponseEntity.ok(userEntityOptional);
        }

        return ResponseEntity.status(HttpStatus.OK).body("User not found");
    }

    public ResponseEntity getListOfUsers() {
        List<UserEntity> userEntityList = userRepository.findByIdIsNotNull();

        return ResponseEntity.ok(userEntityList);
    }

    public ResponseEntity<Object> deleteUserById(int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity saveUser(RegisterForm user){
        if(userRepository.existsByLogin(user.getLogin())){
            return ResponseEntity.status(HttpStatus.OK).body("Login already exists");
        }
        user.setPassword(passwordHashingService.hash(user.getPassword()));
        userRepository.save(new UserEntity(user));

        return ResponseEntity.ok().build();
    }
}
