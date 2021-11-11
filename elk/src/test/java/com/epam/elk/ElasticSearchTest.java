package com.epam.elk;

import com.epam.elk.config.SpringElasticClientConfiguration;
import com.epam.elk.model.Event;
import com.epam.elk.model.SubEvent;
import com.epam.elk.repository.EventRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringElasticClientConfiguration.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ElasticSearchTest {

  private static final String WORKSHOP_TYPE = "WORKSHOP";
  private static final String TECH_TALK_TYPE = "TECH TALK";

  @Autowired
  private ElasticsearchRestTemplate restTemplate;

  @Autowired
  private EventRepository eventRepository;

  @BeforeAll
  void init() {
    SubEvent subEvent = new SubEvent();
    subEvent.setDescription("Sub description");
    subEvent.setTitle("Sub title");

    Event event = new Event();
    event.setEventType("TECH TALK");
    event.setDescription("Description");
    event.setTitle("Title1");
    event.setTags("tech", "interview");
    event.setDateTime(OffsetDateTime.now());
    event.setPlace("Place");
    event.setSubEvents(Collections.singletonList(subEvent));
    eventRepository.save(event);

    event = new Event();
    event.setEventType("WORKSHOP");
    event.setDescription("Description");
    event.setTitle("Title2");
    event.setTags("tech", "tag");
    event.setDateTime(OffsetDateTime.now());
    event.setPlace("Place");
    event.setSubEvents(Collections.singletonList(subEvent));
    eventRepository.save(event);

    event = new Event();
    event.setEventType("WORKSHOP");
    event.setDescription("Description");
    event.setTitle("Title3");
    event.setTags("tech", "tag", "elk");
    event.setDateTime(OffsetDateTime.now());
    event.setPlace("Place");
    event.setSubEvents(Collections.singletonList(subEvent));
    eventRepository.save(event);
  }

  @AfterAll
  void clear() {
    eventRepository.deleteAll();
  }

  @Test
  void createEvent_whenCreated_thenIdPresented() {
    // Given
    SubEvent subEvent = new SubEvent();
    subEvent.setDescription("Sub description");
    subEvent.setTitle("Sub title");

    final List<SubEvent> subEvents = Collections.singletonList(subEvent);

    Event event = createEvent("Title Event", WORKSHOP_TYPE);
    event.setSubEvents(subEvents);

    // When
    event = eventRepository.save(event);

    // Then
    assertNotNull(event.getId());
  }

  @Test
  void getEvents_whenGet_ReturnListOfMatched() {
    // Given
    SubEvent subEvent = new SubEvent();
    subEvent.setDescription("Sub description");
    subEvent.setTitle("Sub title");

    final List<SubEvent> subEvents = Collections.singletonList(subEvent);

    // When
    List<Event> events = eventRepository.findByFilteredTypeQuery(WORKSHOP_TYPE, PageRequest.of(0, 5)).toList();

    // Then
    assertEquals(2, events.size());
  }

  private Event createEvent(String title, String type) {
    Event event = new Event();
    event.setEventType(type);
    event.setDescription("Description");
    event.setTitle(title);
    event.setTags("tech", "interview");
    event.setDateTime(OffsetDateTime.now());
    event.setPlace("Place");
    return event;
  }
}
