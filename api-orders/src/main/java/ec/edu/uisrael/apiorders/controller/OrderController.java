package ec.edu.uisrael.apiorders.controller;

import com.github.javafaker.Faker;
import ec.edu.uisrael.apiorders.constants.Constants;
import ec.edu.uisrael.apiorders.data.Order;
import ec.edu.uisrael.apiorders.data.Storage;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1")
public class OrderController {
    private Faker faker;
    private Storage dataStore = Storage.getInstance();

    @GetMapping("")
    public String hi() {
        return "HI";
    }
    @GetMapping("/order")
    public ResponseEntity<?> getOrders() {
        return new ResponseEntity<>(dataStore.getData(), HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        return new ResponseEntity<>(dataStore.getData(), HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<?> newOrder(@RequestBody Order order) {
        String id = UUID.randomUUID().toString();
        order.setOrderId(id);
        order.setState(Constants.STATE_PENDING);
        dataStore.addElement(id, order);
        return ResponseEntity.ok().build();
    }
}
