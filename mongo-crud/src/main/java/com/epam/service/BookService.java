package com.epam.service;

import com.epam.data.dto.Book;
import com.epam.exception.EntityNotFound;
import com.epam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  private BookRepository repository;

  public List<Book> getAll(final String title, final int page, final int size) {
    Pageable pageable = PageRequest.of(page, size);
    if (title.isEmpty()) {
      return repository.findAll(pageable).getContent();
    } else {
      return getByTitle(title, pageable);
    }
  }

  public void delete(final String id) {
    repository.deleteById(id);
  }

  public Book getById(final String id) {
    return repository.findById(id).orElseThrow(EntityNotFound::new);
  }

  public Book save(final Book book) {
    return repository.insert(book);
  }

  public Book update(final Book book) {
    return repository.save(book);
  }

  public List<Book> getByTitle(final String title, final Pageable pageable) {
    return repository.findByTitleContainingIgnoreCase(title, pageable);
  }
}
