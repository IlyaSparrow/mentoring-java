package com.epam.manager.service;

import com.epam.manager.exception.EntityNotFound;
import com.epam.manager.mapper.TaskMapper;
import com.epam.manager.model.cli.CLITask;
import com.epam.manager.model.entity.Task;
import com.epam.manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    public boolean existsByName(String name) {
        return !repository.findByName(name).isEmpty();
    }

    public boolean existsById(String id) {
        return repository.findById(id).isPresent();
    }

    public List<CLITask> getOverdueTasks() {
        LocalDate now = LocalDate.now();
        List<Task> entityList = repository.findOverdue(now);
        return entityList.stream()
                .map(entity -> mapper.toCLI(entity))
                .collect(Collectors.toList());
    }

    public CLITask findById(final String id) {
        Task entity = repository.findById(id).orElseThrow(EntityNotFound::new);
        return mapper.toCLI(entity);
    }

    public void delete(final String id) {
        repository.deleteById(id);
    }

    public CLITask create(final CLITask task) {
        Task entity = mapper.toEntity(task);
        entity.setDateOfCreation(LocalDate.now());
        return mapper.toCLI(repository.insert(entity));
    }

    public CLITask update(final CLITask task) {
        Task entity = mapper.toEntity(task);
        return mapper.toCLI(repository.save(entity));
    }

    public List<CLITask> getAll() {
        List<Task> entityList = repository.findAll();
        List<CLITask> cliList = entityList.stream()
                .map(entity -> mapper.toCLI(entity))
                .collect(Collectors.toList());
        return cliList;
    }
}
