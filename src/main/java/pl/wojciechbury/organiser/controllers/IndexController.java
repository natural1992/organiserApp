package pl.wojciechbury.organiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wojciechbury.organiser.models.UserSession;
import pl.wojciechbury.organiser.models.forms.NoteForm;
import pl.wojciechbury.organiser.models.services.NoteService;

@Controller
public class IndexController {

    final UserSession userSession;
    final NoteService noteService;

    @Autowired
    public IndexController(UserSession userSession, NoteService noteService){
        this.userSession = userSession;
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String showMainScreen(Model model){
        if(!userSession.isLoggedIn()){
            return "redirect:/user/login";
        }
        model.addAttribute("login", userSession.getUserEntity().getLogin());
        model.addAttribute("notes", noteService.getListOfNotesForToday());

        return "index";
    }

//    @PostMapping("/")
//    public String getMainScreen(Model model, @ModelAttribute @Valid NoteForm noteForm, BindingResult bindingResult){
//
//    }

    @GetMapping("/newnote")
    public String showNewNote(Model model){
        if(!userSession.isLoggedIn()){
            return "redirect:/user/login";
        }
        model.addAttribute("noteForm", new NoteForm());

        return "createNote";
    }

    @PostMapping("/newnote")
    public String getNewNote(Model model, @ModelAttribute NoteForm noteForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("noteInfo", "date or priority is incorrect");
        }
        noteService.addNote(noteForm);

        return "redirect:/";
    }
}
