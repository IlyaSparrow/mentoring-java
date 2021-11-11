package com.epam.elk.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Data
public class SubEvent {

  @Field(type = Text)
  private String title;

  @Field(type = Text)
  private String description;
}
