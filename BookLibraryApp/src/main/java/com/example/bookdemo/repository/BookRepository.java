package com.example.bookdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookdemo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookName(String bookName);
}
