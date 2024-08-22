package com.ys.notes.model.dto;

import lombok.Data;

@Data
public class NoteAddRequest {
    private String title;
    private String content;
}

