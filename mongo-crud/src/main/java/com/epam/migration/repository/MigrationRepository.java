package com.epam.migration.repository;

import com.epam.migration.SQLBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MigrationRepository extends JpaRepository<SQLBook, Integer> {
}
