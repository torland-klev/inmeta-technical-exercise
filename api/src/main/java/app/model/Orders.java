package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Arrays;

import lombok.Data;

import app.tools.exceptions.InvalidServiceException;

@Data
@Entity
public class Orders {

  // The orders ID. Set automatically.
  @Id @GeneratedValue(strategy=GenerationType.AUTO)
  private Long orderId;

  private LocalDateTime lastUpdated;
  private final LocalDateTime created;

  // @Transient ensures that the variable will not be mapped by hibernate.
  // Can easily add/remove supported services.
  @Transient
  private final String[] validServices = {"moving", "packing", "cleaning"};

  private String name;
  // Store mobile as string; we wont be doing any calculations with the value.
  private String mobile;
  private String from;
  private String to;
  private String service;
  private LocalDate date;
  private String comment;

  public Orders(){
    this.lastUpdated = LocalDateTime.now();
    this.created = LocalDateTime.now();
  }

  public void setService(String service) throws InvalidServiceException {
    String serviceLowerCase = service.toLowerCase();
    boolean valid = Arrays.stream(validServices).anyMatch(serviceLowerCase::equals);
    if (valid){
      this.service = serviceLowerCase;
    } else {
      throw new InvalidServiceException(String.format("Supplied service %s is not a valid service. Valid services are %s.", service, Arrays.toString(validServices)));
    }
  }

  @Override
  public String toString(){
    return String.format("Name: %s, mobile: %s, address from: %s, address to: %s, service: %s, moving date: %s, comment: %s", name, mobile, from, to, service, date.toString(), comment);
  }
}
