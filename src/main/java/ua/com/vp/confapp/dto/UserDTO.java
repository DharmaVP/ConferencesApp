package ua.com.vp.confapp.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Blob;

@Data
@EqualsAndHashCode(of = {"id"})
@Builder
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String prefix;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String jobTitle;
    private String organisation;
    private String profileImage;
    private String role;

}
