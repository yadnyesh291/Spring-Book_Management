package com.example.bookdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.bookdemo.model.Book;


import com.example.bookdemo.repository.BookRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @GetMapping("/add-book")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBookSubmit(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/book-details/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        return "book-details";
    }

    @GetMapping("/edit-book/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit-book/{id}")
    public String editBookSubmit(@PathVariable Long id, @ModelAttribute Book editedBook) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/";
        }
        book.setAuthorName(editedBook.getAuthorName());
        book.setBookName(editedBook.getBookName());
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/find-book")
    public String findBookForm() {
        return "find-book";
    }

    @PostMapping("/find-book")
    public String findBookSubmit(@RequestParam String bookName, Model model) {
        Book book = bookRepository.findByBookName(bookName);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        return "book-details";
    }
}
