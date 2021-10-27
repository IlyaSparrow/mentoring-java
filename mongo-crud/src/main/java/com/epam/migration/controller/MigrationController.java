package com.epam.migration.controller;

import com.epam.migration.job.MigrationJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migrate")
public class MigrationController {

  @Autowired
  private MigrationJob migrationJob;

  @PostMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void migrate() {
    migrationJob.migrate();
  }
}
