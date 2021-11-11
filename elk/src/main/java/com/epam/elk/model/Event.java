package com.epam.elk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Auto;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "event")
public class Event {

  @Id
  private String id;

  @MultiField(mainField = @Field(type = Text, fielddata = true), otherFields = { @InnerField(suffix = "verbatim", type = Keyword) })
  private String title;

  @Field(type = Text)
  private String eventType;

  @Field(type = Auto)
  private OffsetDateTime dateTime;

  @Field(type = Text)
  private String place;

  @Field(type = Text)
  private String description;

  @Field(type = Nested, includeInParent = true)
  private List<SubEvent> subEvents;

  @Field(type = Keyword)
  private String[] tags;

  public void setTags(String... tags) {
    this.tags = tags;
  }
}
