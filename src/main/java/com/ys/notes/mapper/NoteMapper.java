package com.ys.notes.mapper;

import com.ys.notes.model.Note;
import com.ys.notes.model.dto.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NoteMapper {

    NoteResponse toNoteResponse(Note note);

}
