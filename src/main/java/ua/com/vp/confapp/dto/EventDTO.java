package ua.com.vp.confapp.dto;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = {"name", "eventDateTime", "placeId"})
@Builder
public class EventDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDateTime;
    private Integer visitors;
    private Long placeId;
    private String building;
    private Short floor;
    private String streetNumber;
    private String streetName;
    private String city;
    private Integer postalCode;
    private String country;

}
