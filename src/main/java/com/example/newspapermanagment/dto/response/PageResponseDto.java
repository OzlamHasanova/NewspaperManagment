package com.example.newspapermanagment.dto.response;

import lombok.Data;

@Data
public class PageResponseDto {
    private Long id;
    private String title;
    private String editorName;
    private String wordDocument;

}