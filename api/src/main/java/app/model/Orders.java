package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Arrays;

/* Cant get Lombok to work with Atom.
import lombok.Data;
import lombok.Getter;
*/
import app.tools.exceptions.InvalidServiceException;

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
  private String addressFrom;
  private String addressTo;
  private String service;
  private LocalDate date;
  private String comment;
  private String email;

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

  public String getService(){
    return this.service;
  }

  public void setOrderId(Long orderId){
    this.orderId = orderId;
    this.lastUpdated = LocalDateTime.now();
  }
  public Long getOrderId(){
    return this.orderId;
  }
  public void setName(String name){
    this.name = name;
    this.lastUpdated = LocalDateTime.now();
  }
  public String getName(){
    return this.name;
  }
  public void setMobile(String mobile){
    this.mobile = mobile;
    this.lastUpdated = LocalDateTime.now();
  }
  public String getMobile(){
    return this.mobile;
  }
  public void setAddressFrom(String addressFrom){
    this.addressFrom = addressFrom;
    this.lastUpdated = LocalDateTime.now();
  }
  public String getAddressFrom(){
    return this.addressFrom;
  }
  public void setAddressTo(String addressTo){
    this.addressTo = addressTo;
    this.lastUpdated = LocalDateTime.now();
  }
  public String getAddressTo(){
    return this.addressTo;
  }
  public void setDate(LocalDate date){
    this.date = date;
    this.lastUpdated = LocalDateTime.now();
  }
  public LocalDate getDate(){
    return this.date;
  }
  public void setComment(String comment){
    this.comment = comment;
    this.lastUpdated = LocalDateTime.now();
  }
  public String getComment(){
    return this.comment;
  }
  public LocalDateTime getCreated(){
    return this.created;
  }
  public LocalDateTime getLastUpdated(){
    return this.lastUpdated;
  }
  public void setEmail(String email){
    this.email = email;
  }
  public String getEmail(){
    return this.email;
  }

  public void setOrder(Orders order){
    this.name = order.getName();
    this.date = order.getDate();
    this.addressFrom = order.getAddressFrom();
    this.addressTo = order.getAddressTo();
    this.service = order.getService();
    this.comment = order.getComment();
    this.lastUpdated = LocalDateTime.now();
  }

  @Override
  public String toString(){
    return String.format("Name: %s, mobile: %s, address addressFrom: %s, address addressTo: %s, service: %s, moving date: %s, comment: %s", name, mobile, addressFrom, addressTo, service, date.toString(), comment);
  }
}
