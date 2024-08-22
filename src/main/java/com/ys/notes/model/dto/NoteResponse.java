package com.ys.notes.model.dto;

import lombok.Data;


@Data
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
}
