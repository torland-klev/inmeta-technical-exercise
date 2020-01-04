package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.ArrayList;

import app.tools.helpers.ReturnObject;
import org.springframework.hateoas.Link;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="")
public class MainController {
  private static final String URL = "http://localhost:9090/v1";

  @Autowired
  private ObjectMapper mapper;

  @GetMapping(path="")
  public ResponseEntity<ObjectNode> welcome() {
    // Use ObjectNode to create customized JSON-response.
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("message", "Welcome to SOPS (Simple Order Placement System) API.");
    ReturnObject ret = new ReturnObject();
    ret.add(new Link(URL + "/orders", "orders"));
    objectNode.put("validEndpoints", mapper.convertValue(ret, JsonNode.class));
    return ResponseEntity.ok(objectNode);
  }

  @GetMapping(path="/orders")
  public ResponseEntity<ObjectNode> welcomeOrders(){
    ObjectNode objectNode = mapper.createObjectNode();
    ReturnObject ret = new ReturnObject();
    ret.add(new Link(URL + "/orders", "valid"));
    objectNode.put("message", "This is not a valid endpoint. Did you mean to type /v1/orders?");
    objectNode.put("links", mapper.convertValue(ret, JsonNode.class));
    return ResponseEntity.ok(objectNode);
  }

  @GetMapping(path="/v1")
  public ResponseEntity<ObjectNode> welcomeV1() {
    // Use ObjectNode to create customized JSON-response.
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("message", "Welcome version 1 to SOPS (Simple Order Placement System) API.");
    List<String> plugins = new ArrayList<>();
    plugins.add("org.springframework.boot version 2.1.7.RELEASE");
    plugins.add("io.spring.dependency-management version 1.0.8.RELEASE");
    plugins.add("java");
    objectNode.put("plugins", mapper.convertValue(plugins, JsonNode.class));

    List<String> dependencies = new ArrayList<>();
    plugins.add("org.springframework.boot:spring-boot-starter-data-jpa");
    plugins.add("org.springframework.boot:spring-boot-starter-web");
    plugins.add("org.postgresql:postgresql");
    plugins.add("org.springframework.boot:spring-boot-starter-hateoas");
    objectNode.put("dependencies", mapper.convertValue(dependencies, JsonNode.class));

    return ResponseEntity.ok(objectNode);
  }
}
