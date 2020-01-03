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

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.repository.OrdersRepository;
import app.model.Orders;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/orders")
public class OrdersController {
  private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

  private @Autowired OrdersRepository ordersRepository;
  private @Autowired ObjectMapper mapper;

  @RequestMapping(method=RequestMethod.GET, params = "id")
  public ResponseEntity<ObjectNode> getOrder(long id){
    ObjectNode objectNode = mapper.createObjectNode();
    try {
      Orders orders = ordersRepository.findById(id).get();
      objectNode.put("id", id);
      objectNode.put("message", "Order found.");
      objectNode.put("Order", mapper.convertValue(orders, JsonNode.class));
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
    objectNode.put("orders", mapper.convertValue(orders, JsonNode.class));
    return ResponseEntity.ok(objectNode);
  }

  @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ObjectNode> createOrder(@RequestBody Orders order){
    ObjectNode objectNode = mapper.createObjectNode();
    try{
      ordersRepository.save(order);
      System.out.println(order.toString());
      objectNode.put("order", mapper.convertValue(ordersRepository.findById(order.getOrderId()).get(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    } catch (Exception e){
      objectNode.put("message", "not created, bad input.");
      objectNode.put("exception", e.toString());
      objectNode.put("stacktrace", mapper.convertValue(e.getStackTrace(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    }
  }

  @RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, params = "id")
  public ResponseEntity<ObjectNode> editOrder(@RequestBody Orders order, long id){
    ObjectNode objectNode = mapper.createObjectNode();
    try {
      Orders thisOrder = ordersRepository.findById(id).get();
      thisOrder.setOrder(order);
      ordersRepository.save(thisOrder);
      System.out.println(thisOrder.toString());
      objectNode.put("order", mapper.convertValue(ordersRepository.findById(thisOrder.getOrderId()).get(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    } catch (Exception e){
      objectNode.put("message", "not updated, bad input.");
      objectNode.put("exception", e.toString());
      objectNode.put("stacktrace", mapper.convertValue(e.getStackTrace(), JsonNode.class));
      return ResponseEntity.ok(objectNode);
    }
  }

}
