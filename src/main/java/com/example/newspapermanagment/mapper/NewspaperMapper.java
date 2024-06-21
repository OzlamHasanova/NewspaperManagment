package com.example.newspapermanagment.mapper;

import com.example.newspapermanagment.dto.request.NewspaperRequestDto;
import com.example.newspapermanagment.dto.request.PageRequestDto;
import com.example.newspapermanagment.dto.response.NewspaperResponseDto;
import com.example.newspapermanagment.dto.response.PageResponseDto;
import com.example.newspapermanagment.entity.Newspaper;
import com.example.newspapermanagment.entity.Page;
import com.example.newspapermanagment.exception.InvalidDateFormatException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@Component
public class NewspaperMapper {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Newspaper toEntity(NewspaperRequestDto dto) {
        Newspaper newspaper = new Newspaper();
        try {
            newspaper.setDate(dateFormat.parse(dto.getDate()));
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + dto.getDate(), e);
        }
        newspaper.setTitle(dto.getTitle());
        newspaper.setEditorName(dto.getEditorName());
        newspaper.setCoverImage(dto.getCoverImage());
        if (dto.getPages() != null) {
            newspaper.setPages(dto.getPages().stream()
                    .map(this::pageRequestDtoToPage)
                    .collect(Collectors.toList()));
        }
        return newspaper;
    }

    public NewspaperResponseDto toDto(Newspaper newspaper) {
        NewspaperResponseDto dto = new NewspaperResponseDto();
        dto.setId(newspaper.getId());
        dto.setDate(dateFormat.format(newspaper.getDate()));
        dto.setTitle(newspaper.getTitle());
        dto.setEditorName(newspaper.getEditorName());
        dto.setCoverImage(newspaper.getCoverImage());
        if (newspaper.getPages() != null) {
            dto.setPages(newspaper.getPages().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public Page pageRequestDtoToPage(PageRequestDto dto) {
        Page page = new Page();
        page.setTitle(dto.getTitle());
        page.setEditorName(dto.getEditorName());
        page.setWordDocument(dto.getWordDocument());
        return page;
    }

    private PageResponseDto toDto(Page page) {
        PageResponseDto dto = new PageResponseDto();
        dto.setId(page.getId());
        dto.setTitle(page.getTitle());
        dto.setEditorName(page.getEditorName());
        dto.setWordDocument(page.getWordDocument());
        return dto;
    }
}
