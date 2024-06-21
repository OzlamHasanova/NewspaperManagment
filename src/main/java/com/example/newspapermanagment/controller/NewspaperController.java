package com.example.newspapermanagment.controller;

import com.example.newspapermanagment.dto.request.NewspaperRequestDto;
import com.example.newspapermanagment.dto.response.NewspaperResponseDto;
import com.example.newspapermanagment.service.NewspaperService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/newspaper")
public class NewspaperController {
    @Autowired
    private NewspaperService newspaperService;

    @GetMapping("/all")
    public List<NewspaperResponseDto> getAllNewspapers() {
        return newspaperService.getAllNewspapers();
    }

    @GetMapping("/{id}/")
    public NewspaperResponseDto getNewspaperById(@PathVariable("id") Long id) {
        return newspaperService.getNewspaperById(id);
    }

    @PostMapping()
    public NewspaperResponseDto addNewspaper(@RequestBody NewspaperRequestDto dto) {
        return newspaperService.addNewspaper(dto);
    }

    @PutMapping("/{id}/")
    public NewspaperResponseDto updateNewspaper(@PathVariable("id") Long id, @RequestBody NewspaperRequestDto dto) {
        return newspaperService.updateNewspaper(id, dto);
    }

    @DeleteMapping("/{id}/")
    public void deleteNewspaper(@PathVariable Long id) {
        newspaperService.deleteNewspaper(id);
    }
}
