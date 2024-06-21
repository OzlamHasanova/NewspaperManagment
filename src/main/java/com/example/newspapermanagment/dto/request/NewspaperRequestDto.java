package com.example.newspapermanagment.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NewspaperRequestDto {
    private String date;
    private String title;
    private String editorName;
    private String coverImage;
    private List<PageRequestDto> pages;

    // Getters and Setters
}