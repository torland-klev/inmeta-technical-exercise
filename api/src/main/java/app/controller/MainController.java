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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="")
public class MainController {

  @Autowired
  private ObjectMapper mapper;

  @GetMapping(path="")
  public ResponseEntity<ObjectNode> welcome() {
    // Use ObjectNode to create customized JSON-response.
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("message", "welcome to SOPS (Simple Order Placement System) API");
    return ResponseEntity.ok(objectNode);
  }
}
