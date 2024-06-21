package com.example.newspapermanagment.service.impl;

import com.example.newspapermanagment.dto.request.PageRequestDto;
import com.example.newspapermanagment.dto.response.PageResponseDto;
import com.example.newspapermanagment.entity.Page;
import com.example.newspapermanagment.exception.InvalidRequestException;
import com.example.newspapermanagment.exception.PageNotFoundException;
import com.example.newspapermanagment.mapper.PageMapper;
import com.example.newspapermanagment.repository.PageRepository;
import com.example.newspapermanagment.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final PageMapper pageMapper;

    @Override
    public PageResponseDto addPage(PageRequestDto dto) {
        if (dto == null) {
            throw new InvalidRequestException("Page request data is invalid.");
        }
        Page page = pageMapper.toEntity(dto);
        return pageMapper.toDto(pageRepository.save(page));
    }

    @Override
    public void deletePage(Long id) {
        if (!pageRepository.existsById(id)) {
            throw new PageNotFoundException("Page not found with id: " + id);
        }
        pageRepository.deleteById(id);
    }

    @Override
    public PageResponseDto updatePage(Long id, PageRequestDto updatedDto) {
        Page existingPage = pageRepository.findById(id)
                .orElseThrow(() -> new PageNotFoundException("Page not found with id: " + id));
        existingPage.setTitle(updatedDto.getTitle());
        existingPage.setEditorName(updatedDto.getEditorName());
        existingPage.setWordDocument(updatedDto.getWordDocument());
        return pageMapper.toDto(pageRepository.save(existingPage));
    }
}