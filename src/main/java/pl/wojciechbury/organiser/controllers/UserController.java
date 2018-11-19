package pl.wojciechbury.organiser.controllers;

import org.springframework.stereotype.Controller;
import pl.wojciechbury.organiser.models.UserSession;
import pl.wojciechbury.organiser.models.services.UserService;

@Controller
public class UserController {

    final UserService userService;
    final UserSession userSession;

    public UserController(UserService userService, UserSession userSession) {
        this.userSession = userSession;
        this.userService = userService;
    }
}
