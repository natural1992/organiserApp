package pl.wojciechbury.organiser.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojciechbury.organiser.models.entities.NoteEntity;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Integer> {
}
