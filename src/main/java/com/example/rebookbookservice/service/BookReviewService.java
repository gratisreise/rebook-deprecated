package com.example.rebookbookservice.service;

import com.example.rebookbookservice.repository.BookRepository;
import com.example.rebookbookservice.repository.BookReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookReviewService {
    private final BookReviewRepository BookReviewRepository;
}
