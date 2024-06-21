package com.example.newspapermanagment.service;

import com.example.newspapermanagment.dto.request.PageRequestDto;
import com.example.newspapermanagment.dto.response.PageResponseDto;
import org.springframework.stereotype.Service;

public interface PageService {
    PageResponseDto addPage(PageRequestDto dto);
    void deletePage(Long id);
    PageResponseDto updatePage(Long id, PageRequestDto updatedDto);
}
