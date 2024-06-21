package com.example.newspapermanagment.mapper;

import com.example.newspapermanagment.dto.request.PageRequestDto;
import com.example.newspapermanagment.dto.response.PageResponseDto;
import com.example.newspapermanagment.entity.Page;
import com.example.newspapermanagment.exception.NewspaperNotFoundException;
import com.example.newspapermanagment.repository.NewspaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageMapper {

    private final NewspaperRepository newspaperRepository;

    public Page toEntity(PageRequestDto dto) {
        Page page = new Page();
        page.setTitle(dto.getTitle());
        page.setEditorName(dto.getEditorName());
        page.setWordDocument(dto.getWordDocument());
        page.setNewspaper(newspaperRepository.findById(dto.getNewspaperId())
                .orElseThrow(() -> new NewspaperNotFoundException("Newspaper not found with id: " + dto.getNewspaperId())));
        return page;
    }

    public PageResponseDto toDto(Page page) {
        PageResponseDto dto = new PageResponseDto();
        dto.setId(page.getId());
        dto.setTitle(page.getTitle());
        dto.setEditorName(page.getEditorName());
        dto.setWordDocument(page.getWordDocument());
        return dto;
    }
}
