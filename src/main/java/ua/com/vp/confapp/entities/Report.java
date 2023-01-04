package ua.com.vp.confapp.entities;

import java.util.Objects;

public class Report extends Entity {

   private String topic;
   private String outline;
   private Event event;
   private User speaker;
   private Boolean accepted;


   public String getTopic() {
      return topic;
   }

   public void setTopic(String topic) {
      this.topic = topic;
   }

   public String getOutline() {
      return outline;
   }

   public void setOutline(String outline) {
      this.outline = outline;
   }

   public Event getEvent() {
      return event;
   }

   public void setEvent(Event event) {
      this.event = event;
   }

   public User getSpeaker() {
      return speaker;
   }

   public void setSpeaker(User speaker) {
      this.speaker = speaker;
   }

   public Boolean getAccepted() {
      return accepted;
   }

   public void setAccepted(Boolean accepted) {
      this.accepted = accepted;
   }



   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Report report = (Report) o;
      return Objects.equals(id, report.id);
   }

   @Override
   public int hashCode() {
      return (id != null)
              ? (this.getClass().hashCode() + id.hashCode())
              : super.hashCode();
   }

   @Override
   public String toString() {
      return "Report{" +
              "id=" + id +
              ", topic='" + topic + '\'' +
              ", outline='" + outline + '\'' +
              ", event=" + event +
              ", speaker=" + speaker +
              ", accepted=" + accepted +
              "}+\n";
   }
}
