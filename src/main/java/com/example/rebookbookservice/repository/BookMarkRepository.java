package com.example.rebookbookservice.repository;

import com.example.rebookbookservice.model.entity.BookMark;
import com.example.rebookbookservice.model.entity.compositekey.BookMarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, BookMarkId> {

}
