package com.epam.migration.service;

import com.epam.data.dto.Book;
import com.epam.migration.SQLBook;
import com.epam.migration.mapper.Mapper;
import com.epam.migration.repository.MigrationRepository;
import com.epam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MigrationService {

  @Autowired
  private MigrationRepository repository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private Mapper mapper;

  public List<Book> getAll(final Pageable pageable) {
    final List<SQLBook> sqlBooks = repository.findAll(pageable).getContent();
    return mapper.map(sqlBooks);
  }

  public long getCount() {
    return repository.count();
  }

  public List<Book> saveInMongo(final List<Book> books) {
    return bookRepository.saveAll(books);
  }
}
