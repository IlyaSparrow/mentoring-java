package com.epam.migration.job;

import com.epam.data.dto.Book;
import com.epam.migration.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MigrationJob {

  private static final int PER_PAGE = 100;

  @Autowired
  private MigrationService service;

  public void migrate() {
    final long count = service.getCount();
    final long pages = count / PER_PAGE;
    int currentPage = 0;

    while (currentPage <= pages) {
      Pageable pageable = PageRequest.of(currentPage, PER_PAGE);
      List<Book> books = service.getAll(pageable);

      service.saveInMongo(books);

      currentPage++;
    }
  }
}
