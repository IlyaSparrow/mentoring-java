package com.epam.migration.mapper;

import com.epam.data.dto.Book;
import com.epam.migration.SQLBook;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

  public List<Book> map(final List<SQLBook> sqlBooks) {
    return sqlBooks.stream()
        .map(this::mapBook)
        .collect(Collectors.toList());
  }

  public Book mapBook(final SQLBook sqlBook) {
    return Book.builder()
        .author(sqlBook.getAuthor())
        .description(sqlBook.getDescription())
        .title(sqlBook.getTitle())
        .build();
  }
}
