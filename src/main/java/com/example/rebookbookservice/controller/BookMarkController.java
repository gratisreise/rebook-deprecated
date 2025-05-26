package com.example.rebookbookservice.controller;

import com.example.rebookbookservice.common.CommonResult;
import com.example.rebookbookservice.common.ResponseService;
import com.example.rebookbookservice.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookMarkController {
    private final BookMarkService bookMarkService;

    //@RequestHeader("X-User-Id") String userId
    @PostMapping("/{bookId}/marks")
    public CommonResult markingToggle(@RequestParam String userId, @PathVariable Long bookId) {
        bookMarkService.markingToggle(userId, bookId);
        return ResponseService.getSuccessResult();
    }

}
