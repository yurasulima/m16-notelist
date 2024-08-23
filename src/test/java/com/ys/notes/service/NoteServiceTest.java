package com.ys.notes.service;

import com.ys.notes.mapper.NoteMapperImpl;
import com.ys.notes.model.Note;
import com.ys.notes.model.dto.NoteResponse;
import com.ys.notes.model.dto.NoteUpdateRequest;
import com.ys.notes.repository.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;
    @Spy
    private NoteMapperImpl noteMapper;

    @InjectMocks
    private NoteService noteService;


    @Test
    void testNoteIsUpdatedSuccessfully() {
        //given
        NoteUpdateRequest request = new NoteUpdateRequest();
        request.setTitle("Title");
        request.setContent("Content");

        Note note = new Note();
        note.setTitle("Title");
        note.setContent("Content");

        when(noteRepository.findById(1L))
                .thenReturn(Optional.of(note));

        //when
        NoteResponse result = noteService.update(1L, request);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(note);

        verify(noteRepository).findById(1L);
        verify(noteMapper).toNoteResponse(note);
    }
}