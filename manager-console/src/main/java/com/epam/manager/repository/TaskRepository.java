package com.epam.manager.repository;

import com.epam.manager.model.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByName(String name);
    List<Task> findAll();

    @Query("{deadline: {$lt:?0}}")
    List<Task> findOverdue(LocalDate currentDate);
}
