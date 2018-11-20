package pl.wojciechbury.organiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wojciechbury.organiser.models.UserSession;
import pl.wojciechbury.organiser.models.dtos.WeatherDto;
import pl.wojciechbury.organiser.models.forms.NoteForm;
import pl.wojciechbury.organiser.models.services.NoteService;
import pl.wojciechbury.organiser.models.services.WeatherService;

import java.time.LocalDate;

@Controller
public class IndexController {

    final UserSession userSession;
    final NoteService noteService;
    final WeatherService weatherService;

    @Autowired
    public IndexController(UserSession userSession, NoteService noteService, WeatherService weatherService){
        this.userSession = userSession;
        this.noteService = noteService;
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String showMainScreen(Model model){
        WeatherDto weather = weatherService.loadWeatherFor(userSession.getUserEntity().getCity());

        model.addAttribute("login", userSession.getUserEntity().getLogin());
        model.addAttribute("notes", noteService.getListOfNotesForToday());
        model.addAttribute("weather", (int) weather.getTempDto().getTemperature() - 273);
        model.addAttribute("clouds", (int) weather.getCloudsDto().getClouds());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("city", userSession.getUserEntity().getCity());

        return "index";
    }

    @GetMapping("/newnote")
    public String showNewNote(Model model){
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
