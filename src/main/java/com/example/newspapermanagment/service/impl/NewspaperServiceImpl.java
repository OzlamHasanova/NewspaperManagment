package com.example.newspapermanagment.service.impl;

import com.example.newspapermanagment.dto.request.NewspaperRequestDto;
import com.example.newspapermanagment.dto.response.NewspaperResponseDto;
import com.example.newspapermanagment.entity.Page;
import com.example.newspapermanagment.exception.InvalidRequestException;
import com.example.newspapermanagment.exception.NewspaperNotFoundException;
import com.example.newspapermanagment.mapper.NewspaperMapper;
import com.example.newspapermanagment.entity.Newspaper;
import com.example.newspapermanagment.repository.NewspaperRepository;
import com.example.newspapermanagment.service.NewspaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewspaperServiceImpl implements NewspaperService {

    private final NewspaperRepository newspaperRepository;
    private final NewspaperMapper newspaperMapper;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public NewspaperResponseDto addNewspaper(NewspaperRequestDto dto) {
        if (dto == null) {
            throw new InvalidRequestException("Newspaper request data is invalid.");
        }
        Newspaper newspaper = newspaperMapper.toEntity(dto);
        return newspaperMapper.toDto(newspaperRepository.save(newspaper));
    }

    @Override
    public List<NewspaperResponseDto> getAllNewspapers() {
        return newspaperRepository.findAll().stream()
                .map(newspaperMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewspaperResponseDto getNewspaperById(Long id) {
        Newspaper newspaper = newspaperRepository.findById(id)
                .orElseThrow(() -> new NewspaperNotFoundException("Newspaper not found with id: " + id));
        return newspaperMapper.toDto(newspaper);
    }

    @Override
    public void deleteNewspaper(Long id) {
        if (!newspaperRepository.existsById(id)) {
            throw new NewspaperNotFoundException("Newspaper not found with id: " + id);
        }
        newspaperRepository.deleteById(id);
    }

    @Override
    public NewspaperResponseDto updateNewspaper(Long id, NewspaperRequestDto updatedDto) {
        Newspaper existingNewspaper = newspaperRepository.findById(id)
                .orElseThrow(() -> new NewspaperNotFoundException("Newspaper not found with id: " + id));
        try {
            existingNewspaper.setDate(dateFormat.parse(updatedDto.getDate()));
        } catch (ParseException e) {
            throw new InvalidRequestException("Invalid date format: " + updatedDto.getDate());
        }
        existingNewspaper.setTitle(updatedDto.getTitle());
        existingNewspaper.setEditorName(updatedDto.getEditorName());
        existingNewspaper.setCoverImage(updatedDto.getCoverImage());

        if (updatedDto.getPages() != null) {
            List<Page> pages = updatedDto.getPages().stream()
                    .map(pageRequestDto -> newspaperMapper.pageRequestDtoToPage(pageRequestDto))
                    .collect(Collectors.toList());
            existingNewspaper.setPages(pages);
        }

        return newspaperMapper.toDto(newspaperRepository.save(existingNewspaper));
    }
}
