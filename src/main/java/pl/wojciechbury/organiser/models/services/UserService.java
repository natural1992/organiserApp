package pl.wojciechbury.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wojciechbury.organiser.models.UserSession;
import pl.wojciechbury.organiser.models.entities.UserEntity;
import pl.wojciechbury.organiser.models.forms.RegisterForm;
import pl.wojciechbury.organiser.models.forms.UserForm;
import pl.wojciechbury.organiser.models.repositories.UserRepository;

import javax.jws.soap.SOAPBinding;

@Service
public class UserService {

    final UserRepository userRepository;
    final PasswordHashingService passwordHashingService;
    final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHashingService passwordHashingService, UserSession userSession){
        this.userRepository = userRepository;
        this.passwordHashingService = passwordHashingService;
        this.userSession = userSession;
    }


    public boolean isSuchLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public void tryLogin(UserForm userForm) {
        UserEntity loginUser = userRepository.getUserByLogin(userForm.getLogin()).get();

        if(passwordHashingService.match(loginUser.getPassword(), userForm.getPassword()));{
            userSession.setLoggedIn(true);
            userSession.setUserEntity(loginUser);
        }
    }

    public void addUser(RegisterForm registerForm) {
        registerForm.setPassword(passwordHashingService.hash(registerForm.getPassword()));
        UserEntity newUser = new UserEntity(registerForm);
        userRepository.save(newUser);
    }

    public boolean isRepeatedPasswordCorrect(RegisterForm registerForm) {
        return registerForm.getPassword().equals(registerForm.getPasswordRepeat());
    }
}
