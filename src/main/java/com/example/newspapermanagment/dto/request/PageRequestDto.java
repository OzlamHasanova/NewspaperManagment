package com.example.newspapermanagment.dto.request;

import lombok.Data;

@Data
public class PageRequestDto {
    private String title;
    private String editorName;
    private String wordDocument;
    private Long newspaperId;
}
