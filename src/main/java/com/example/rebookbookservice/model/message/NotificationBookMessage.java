package com.example.rebookbookservice.model.message;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationBookMessage implements Serializable {
    @NotBlank
    private String message;

    @NotBlank
    private String type;

    @NotBlank
    private String bookId;

    @NotBlank
    private String category;


    public NotificationBookMessage(Long bookId, String category, String message) {
        this.message = message;
        this.type = "BOOK";
        this.bookId = bookId.toString();
        this.category = category;
    }
}
