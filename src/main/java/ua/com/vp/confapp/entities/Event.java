package ua.com.vp.confapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event extends Entity {

    private String name;
    private String description;
    private LocalDateTime dateTime;
    private Integer visitors;
    private EventAddress address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    public EventAddress getAddress() {
        return address;
    }

    public void setAddress(EventAddress address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", visitors=" + visitors +
                ", address=" + address +
                "}+\n";
    }


    public static class EventAddress extends Entity {

        private String buildingName;
        private Short floor;
        private String streetNumber;
        private String streetName;
        private String city;
        private Integer postalCode;
        private String country;


        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public Short getFloor() {
            return floor;
        }

        public void setFloor(Short floor) {
            this.floor = floor;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(Integer postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EventAddress that = (EventAddress) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return (id != null)
                    ? (this.getClass().hashCode() + id.hashCode())
                    : super.hashCode();
        }

        @Override
        public String toString() {
            return "EventAddress{" +
                    "id=" + id +
                    ", buildingName='" + buildingName + '\'' +
                    ", floor=" + floor +
                    ", streetNumber='" + streetNumber + '\'' +
                    ", streetName='" + streetName + '\'' +
                    ", city='" + city + '\'' +
                    ", postalCode=" + postalCode +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
