package com.example.newspapermanagment.service;

import com.example.newspapermanagment.dto.request.NewspaperRequestDto;
import com.example.newspapermanagment.dto.response.NewspaperResponseDto;
import com.example.newspapermanagment.entity.Newspaper;

import java.util.List;

public interface NewspaperService {
    NewspaperResponseDto addNewspaper(NewspaperRequestDto dto);
    List<NewspaperResponseDto> getAllNewspapers();
    NewspaperResponseDto getNewspaperById(Long id);
    void deleteNewspaper(Long id);
    NewspaperResponseDto updateNewspaper(Long id, NewspaperRequestDto updatedDto);

}
