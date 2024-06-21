package com.example.newspapermanagment.controller;

import com.example.newspapermanagment.dto.request.PageRequestDto;
import com.example.newspapermanagment.dto.response.PageResponseDto;
import com.example.newspapermanagment.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;
    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping("/add")
    public ResponseEntity<PageResponseDto> addPage(@RequestBody PageRequestDto dto) {
        PageResponseDto responseDto = pageService.addPage(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        pageService.deletePage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageResponseDto> updatePage(@PathVariable Long id,
                                                      @RequestBody PageRequestDto updatedDto) {
        PageResponseDto responseDto = pageService.updatePage(id, updatedDto);
        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
