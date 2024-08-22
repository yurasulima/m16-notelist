package com.ys.notes.service;

import com.ys.notes.exception.BadRequestException;
import com.ys.notes.mapper.NoteMapper;
import com.ys.notes.model.dto.NoteAddRequest;
import com.ys.notes.model.dto.NoteResponse;
import com.ys.notes.model.dto.NoteUpdateRequest;
import com.ys.notes.repository.NoteRepository;
import com.ys.notes.model.Note;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

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

    @Transactional
    public NoteResponse update(Long id, NoteUpdateRequest request) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return noteMapper.toNoteResponse(note);
    }
    
    @Transactional
    public NoteResponse create(NoteAddRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        noteRepository.save(note);
        return noteMapper.toNoteResponse(note);
    }

    public NoteResponse getById(Long id) {
        return noteMapper.toNoteResponse(
                noteRepository.findById(id).orElseThrow(BadRequestException::new)
        );
    }

    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}