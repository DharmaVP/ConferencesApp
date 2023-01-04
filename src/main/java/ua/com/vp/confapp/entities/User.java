package ua.com.vp.confapp.entities;


import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.exception.CommandException;
import ua.com.vp.confapp.exception.ValidationException;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Stream;

public class User extends Entity {

    private String email;
    private transient String password;
    private Prefix prefix;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String jobTitle;
    private String organisation;
    private Instant registrationDate;
    private String profileImagePath;
    private Role role;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Instant registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getProfileImage() {
        return profileImagePath;
    }

    public void setProfileImage(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * The user ID is unique for each User. So this should compare User by ID only.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.getId());
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", prefix=" + prefix +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", organisation='" + organisation + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                "}\n";
    }

    public enum Prefix {
        MR("Mr."),
        MRS("Mrs."),
        MS("Ms."),
        MISS("Miss"),
        DR("Dr."),
        PROF("Prof."),
        SIR("Sir"),
        LADY("Lady"),
        LORD("Lord"),
        FATHER("Father"),
        SISTER("Sister"),
        REV("Rev.");
        String name;

        Prefix(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Prefix{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public static Prefix of(String name) {
            return Stream.of(Prefix.values())
                    .filter(c -> c.getName().equalsIgnoreCase(name))
                    .findFirst().orElse(null);
        }
    }


    public static class Role extends Entity {

        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Role role = (Role) o;
            return Objects.equals(id, role.getId());
        }

        @Override
        public int hashCode() {
            return (id != null)
                    ? (this.getClass().hashCode() + id.hashCode())
                    : super.hashCode();
        }

        @Override
        public String toString() {
            return "Role{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
