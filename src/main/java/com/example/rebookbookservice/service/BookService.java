package com.example.rebookbookservice.service;

import com.example.rebookbookservice.model.BookRequest;
import com.example.rebookbookservice.model.entity.Book;
import com.example.rebookbookservice.model.naver.NaverBooksResponse;
import com.example.rebookbookservice.repository.BookRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ApiService apiService;

    public NaverBooksResponse searchNaverBooks(String keyword) {
        return apiService.searchBooks(keyword);
    }

    @Transactional
    public void postBook(BookRequest request) {
        String category = apiService.getCategory(request.getTitle());
        LocalDate publishedDate = LocalDate.parse(request.getPublishedDate(), DateTimeFormatter.BASIC_ISO_DATE);
        Book book = new Book(request, category, publishedDate);
        bookRepository.save(book);
    }


}
