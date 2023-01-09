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

    public static UserDTO convertToUserDTO (User user){
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

    public static Event convertToEvent (EventDTO eventDTO){
        Event event = new Event();
        return event;
    }

    public static EventDTO convertToEventDTO (Event event){
        return EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .eventDateTime(event.getDateTime())
                .build();
    }

    public static Report convertToReport(ReportDTO reportDTO){
        Report report = new Report();
        return report;
    }

    public static ReportDTO convertToReportDTO(Report report){
        return ReportDTO.builder().build();
    }
}
