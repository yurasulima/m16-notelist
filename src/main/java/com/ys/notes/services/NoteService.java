package com.ys.notes.services;

import com.ys.notes.repository.NoteRepository;
import com.ys.notes.models.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }
    public Note add(Note note) {
        noteRepository.save(note);
        return note;
    }

    public void deleteById(long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void update(Note note) {
        if (noteRepository.existsById(note.getId())) {
            noteRepository.save(note);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}