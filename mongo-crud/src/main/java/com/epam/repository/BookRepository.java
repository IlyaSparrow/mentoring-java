package com.epam.repository;

import com.epam.data.dto.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

  List<Book> findByTitleContainingIgnoreCase(final String title, final Pageable pageable);
}
