package com.epam.elk.repository;

import com.epam.elk.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ElasticsearchRepository<Event, String> {

  Page<Event> findByTitle(String title, Pageable pageable);

  @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
  Page<Event> findByTitleWithQuery(String title, Pageable pageable);

  @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"type\": \"?0\" }}}}")
  Page<Event> findByFilteredTypeQuery(String tag, Pageable pageable);
}
