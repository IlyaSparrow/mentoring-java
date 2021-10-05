package com.epam.event.contracts;

import com.epam.event.dto.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Event  extends RepresentationModel<Event> {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    private String title;

    @NotBlank
    @Size(min = 3, max = 30)
    private String place;

    @NotBlank
    @Size(min = 3, max = 30)
    private String speaker;

    @NotBlank
    private EventType eventType;

    @NotBlank
    private LocalDate dateTime;
}
