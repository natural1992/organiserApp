package pl.wojciechbury.organiser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wojciechbury.organiser.models.UserSession;
import pl.wojciechbury.organiser.models.forms.RegisterForm;
import pl.wojciechbury.organiser.models.forms.UserForm;
import pl.wojciechbury.organiser.models.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    final UserService userService;
    final UserSession userSession;

    public UserController(UserService userService, UserSession userSession) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String showLoginForm(Model model){
        model.addAttribute("userForm", new UserForm());

        return "userLogin";
    }

    @PostMapping("/user/login")
    public String getLoginForm(Model model, @ModelAttribute UserForm userForm){
        if(!userService.isSuchLogin(userForm.getLogin())){
            model.addAttribute("loginInfo", "Wrong login");
        }
        userService.tryLogin(userForm);

        if(userSession.isLoggedIn()){
            return "redirect:/";
        }

        return "userLogin";
    }

    @GetMapping("/user/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());

        return "userRegistration";
    }

    @PostMapping("/user/register")
    public String getRegistrationForm(Model model, @ModelAttribute @Valid RegisterForm registerForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("registerInfo", "Some of the data is incorrect");

            return "userRegistration";
        }
        if(userService.isSuchLogin(registerForm.getLogin())){
            model.addAttribute("registerInfo", "This login is already taken");

            return "userRegistration";
        }
        if(!userService.isRepeatedPasswordCorrect(registerForm)){
            model.addAttribute("registerInfo", "Repeated password is incorrect");

            return "userRegistration";
        }
        userService.addUser(registerForm);

        UserForm newUser = new UserForm(registerForm.getLogin(), registerForm.getPassword());

        userService.tryLogin(newUser);

        return "redirect:/";
    }
}
