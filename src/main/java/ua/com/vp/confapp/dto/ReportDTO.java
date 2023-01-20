package ua.com.vp.confapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.vp.confapp.entities.User;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(of = {"topic", "eventId"})
@Builder
public class ReportDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String topic;
    private String outline;

    private Long speakerId;
    private String prefix;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String organisation;

    private Long eventId;

    private Boolean accepted;
}
