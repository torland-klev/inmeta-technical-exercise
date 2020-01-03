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
    objectNode.put("id", id);
    Orders Orders = orderRepository.findById(id).get();
    if (Orders == null){
      return ResponseEntity.notFound().build();
    }
    objectNode.put("message", "Orders found.");

    objectNode.put("Orders", mapper.convertValue(Orders, JsonNode.class));
    return ResponseEntity.ok(objectNode);

  }

}
