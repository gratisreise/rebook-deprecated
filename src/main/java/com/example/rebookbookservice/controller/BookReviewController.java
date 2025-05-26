package com.example.rebookbookservice.controller;

import com.example.rebookbookservice.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookReviewController {
    private final BookReviewService bookReviewService;

}
