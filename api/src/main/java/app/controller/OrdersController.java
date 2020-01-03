package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.repository.OrderRepository;
import app.model.Order;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/orders")
public class OrderController {
  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  private @Autowired OrderRepository orderRepository;
  private @Autowired ObjectMapper mapper;

  @RequestMapping(method=RequestMethod.GET, params = "id")
  public ResponseEntity<ObjectNode> getOrder(long id){
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("id", id);
    Order order = orderRepository.findById(id).get();
    if (order == null){
      return ResponseEntity.notFound().build();
    }
    objectNode.put("message", "Order found.");

    objectNode.put("order", mapper.convertValue(order, JsonNode.class));
    return ResponseEntity.ok(objectNode);

  }

}
