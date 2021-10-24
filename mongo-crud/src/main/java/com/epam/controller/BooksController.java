package com.epam.controller;

import com.epam.data.dto.Book;
import com.epam.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

  @Autowired
  private BookService service;

  @GetMapping("?{title}?{page}?{size}")
  @ResponseStatus(HttpStatus.OK)
  public List<Book> getAll(@RequestParam(required = false) String title,
      @RequestParam(defaultValue = "0") final int page,
      @RequestParam(defaultValue = "5") final int size) {
    return service.getAll(title, page, size);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book save(@RequestBody final Book book) {
    return service.save(book);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Book getById(@PathVariable("id") final String id) {
    return service.getById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") final String id) {
    service.delete(id);
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Book update(@RequestBody final Book book) {
    return service.update(book);
  }
}
