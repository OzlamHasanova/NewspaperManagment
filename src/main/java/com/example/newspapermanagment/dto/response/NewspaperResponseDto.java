package com.example.newspapermanagment.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class NewspaperResponseDto {
    private Long id;
    private String date;
    private String title;
    private String editorName;
    private String coverImage;
    private List<PageResponseDto> pages;

}

