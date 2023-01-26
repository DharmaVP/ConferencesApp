package ua.com.vp.confapp.utils;

import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.Report;
import ua.com.vp.confapp.entities.User;

public class MapperDTO {

    public static User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPrefix(User.Prefix.of(userDTO.getPrefix()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setJobTitle(userDTO.getJobTitle());
        user.setOrganisation(userDTO.getOrganisation());
        user.setProfileImage(userDTO.getProfileImage());
        User.Role role = new User.Role();
        role.setName(userDTO.getRole());
        user.setRole(role);
        return user;
    }

    public static UserDTO convertToUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .prefix((user.getPrefix()==null) ? null : user.getPrefix().getName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .jobTitle(user.getJobTitle())
                .organisation(user.getOrganisation())
                .profileImage(user.getProfileImage())
                .role(user.getRole().getName())
                .build();
    }

    public static Event convertDTOToEvent(EventDTO eventDTO){
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDateTime(eventDTO.getEventDateTime());
        event.setVisitors(eventDTO.getVisitors());
        Event.EventAddress address = new Event.EventAddress();
        address.setId(eventDTO.getPlaceId());
        address.setBuildingName(eventDTO.getBuilding());
        address.setFloor(eventDTO.getFloor());
        address.setStreetNumber(eventDTO.getStreetNumber());
        address.setStreetName(eventDTO.getStreetName());
        address.setCity(eventDTO.getCity());
        address.setPostalCode(eventDTO.getPostalCode());
        address.setCountry(eventDTO.getCountry());
        event.setAddress(address);
        return event;
    }

    public static EventDTO convertToEventDTO(Event event){
        return EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .eventDateTime(event.getDateTime())
                .visitors(event.getVisitors())
                .participants(event.getParticipants())
                .numberOfReports(event.getReports())
                .placeId(event.getAddress().getId())
                .building(event.getAddress().getBuildingName())
                .floor(event.getAddress().getFloor())
                .streetNumber(event.getAddress().getStreetNumber())
                .streetName(event.getAddress().getStreetName())
                .city(event.getAddress().getCity())
                .postalCode(event.getAddress().getPostalCode())
                .country(event.getAddress().getCountry())
                .build();
    }

    public static Report convertToReport(ReportDTO reportDTO){
        Report report = new Report();
        User speaker = new User();
        Event event = new Event();
        report.setId(reportDTO.getId());
        report.setTopic(reportDTO.getTopic());
        report.setOutline(reportDTO.getOutline());
        speaker.setId(reportDTO.getSpeakerId());
        report.setSpeaker(speaker);
        event.setId(reportDTO.getEventId());
        report.setEvent(event);
        report.setAcceptedByModerator(reportDTO.getAcceptedByModerator());
        report.setAcceptedBySpeaker(reportDTO.getAcceptedBySpeaker());
        return report;
    }

    public static ReportDTO convertToReportDTO(Report report){
        return ReportDTO.builder()
                .id(report.getId())
                .topic(report.getTopic())
                .outline(report.getOutline())
                .speakerId(report.getSpeaker() == null ? null : report.getSpeaker().getId())
                .prefix(report.getSpeaker() == null ? null : report.getSpeaker().getPrefix() == null ? null : report.getSpeaker().getPrefix().getName())
                .firstName(report.getSpeaker() == null ? null : report.getSpeaker().getFirstName())
                .lastName(report.getSpeaker() == null ? null : report.getSpeaker().getLastName())
                .jobTitle(report.getSpeaker() == null ? null : report.getSpeaker().getJobTitle())
                .organisation(report.getSpeaker() == null ? null : report.getSpeaker().getOrganisation())
                .eventId(report.getEvent().getId())
                .acceptedByModerator(report.getAcceptedByModerator())
                .acceptedBySpeaker(report.getAcceptedBySpeaker())
                .build();
    }
}
