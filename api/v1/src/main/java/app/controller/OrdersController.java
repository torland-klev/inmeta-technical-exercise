package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.repository.OrdersRepository;
import app.model.Orders;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/v1/orders")
public class OrdersController {
  private static final String URL = "http://localhost:9090/v1/orders";

  private @Autowired OrdersRepository ordersRepository;
  private @Autowired ObjectMapper mapper;

  @RequestMapping(method=RequestMethod.GET, params = "id")
  public ResponseEntity<ObjectNode> getOrder(Long id){
    System.out.println(id);
    ObjectNode objectNode = mapper.createObjectNode();
    try {
      Orders order = ordersRepository.findById(id).get();
      addLinks(order);
      objectNode.put("id", id);
      objectNode.put("message", "Order found.");
      objectNode.put("Order", mapper.convertValue(order, JsonNode.class));
      return ResponseEntity.ok(objectNode);
    } catch(Exception e){
      objectNode.put("message", "order not found, bad id.");
      objectNode.put("exception", e.toString());
      objectNode.put("stacktrace", mapper.convertValue(e.getStackTrace(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    }
  }

  @RequestMapping(method=RequestMethod.GET)
  public ResponseEntity<ObjectNode> getAllOrders(){
    ObjectNode objectNode = mapper.createObjectNode();
    Iterable<Orders> orders = ordersRepository.findAll();
    addLinks(orders);
    objectNode.put("orders", mapper.convertValue(orders, JsonNode.class));
    return ResponseEntity.ok(objectNode);
  }

  @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ObjectNode> createOrder(@RequestBody Orders order){
    ObjectNode objectNode = mapper.createObjectNode();
    try{
      ordersRepository.save(order);
      addLinks(order);
      objectNode.put("order", mapper.convertValue(ordersRepository.findById(order.getOrderId()).get(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    } catch (Exception e){
      objectNode.put("message", "Not created, bad input.");
      objectNode.put("exception", e.toString());
      objectNode.put("stacktrace", mapper.convertValue(e.getStackTrace(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    }
  }

  @RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, params = "id")
  public ResponseEntity<ObjectNode> editOrder(@RequestBody Orders order, Long id){
    ObjectNode objectNode = mapper.createObjectNode();
    try {
      Orders thisOrder = ordersRepository.findById(id).get();
      thisOrder.setOrder(order);
      ordersRepository.save(thisOrder);
      objectNode.put("order", mapper.convertValue(ordersRepository.findById(thisOrder.getOrderId()).get(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    } catch (Exception e){
      objectNode.put("message", "Not updated, bad input.");
      objectNode.put("exception", e.toString());
      objectNode.put("stacktrace", mapper.convertValue(e.getStackTrace(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    }
  }

  @RequestMapping(method=RequestMethod.DELETE, params = "id")
  public ResponseEntity<ObjectNode> deleteOrder(Long id){
    ObjectNode objectNode = mapper.createObjectNode();
    try {
      Orders order = ordersRepository.findById(id).get();
      ordersRepository.delete(order);
      objectNode.put("message", "Successfully deleted order.");
      objectNode.put("deleted order", mapper.convertValue(order, JsonNode.class));
      return ResponseEntity.ok(objectNode);
    } catch (Exception e){
      objectNode.put("message", "Not deleted, bad id.");
      objectNode.put("exception", e.toString());
      objectNode.put("stacktrace", mapper.convertValue(e.getStackTrace(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    }
  }

  private void addLinks(Orders order){
    Long id = order.getOrderId();
    // Add some Hypermedia Links
    order.add(new Link(URL + "?id=" + id, IanaLinkRelations.SELF));
    order.add(new Link(URL + "?id=" + (id+1), IanaLinkRelations.NEXT));
    Orders first = ordersRepository.findTopByOrderByOrderIdAsc();
    order.add(new Link(URL + "?id=" + (first.getOrderId()), IanaLinkRelations.FIRST));
    Orders last = ordersRepository.findTopByOrderByOrderIdDesc();
    order.add(new Link(URL + "?id=" + (last.getOrderId()), IanaLinkRelations.LAST));
    order.add(new Link(URL, "all"));
    if (id > 1){
      order.add(new Link(URL + "?id=" + (id-1), IanaLinkRelations.PREV));
    }
  }

  private void addLinks(Iterable<Orders> orders){
    for(Orders o : orders){
      addLinks(o);
    }
  }

}
