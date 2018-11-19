package pl.wojciechbury.organiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wojciechbury.organiser.models.UserSession;

@Controller
public class IndexController {

    final UserSession userSession;

    @Autowired
    public IndexController(UserSession userSession){
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String showMainScreen(Model model){
        if(!userSession.isLoggedIn()){
            return "redirect:/user/login";
        }
        model.addAttribute("login", userSession.getUserEntity().getLogin());

        return "index";
    }
}
