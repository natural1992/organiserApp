package pl.wojciechbury.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wojciechbury.organiser.models.UserSession;
import pl.wojciechbury.organiser.models.entities.NoteEntity;
import pl.wojciechbury.organiser.models.forms.NoteForm;
import pl.wojciechbury.organiser.models.repositories.NoteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NoteService {

    final UserSession userSession;
    final NoteRepository noteRepository;

    @Autowired
    public NoteService(UserSession userSession, NoteRepository noteRepository){
        this.userSession = userSession;
        this.noteRepository = noteRepository;
    }

    private LocalDate convertStringToLocalDate(String stringDate){
        LocalDate dateFromUserInput = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dateFromUserInput;
    }

    public void addNote(NoteForm noteForm) {
        NoteEntity newNote = new NoteEntity(noteForm, userSession, convertStringToLocalDate(noteForm.getNoteDate()));
        noteRepository.save(newNote);
    }
}
