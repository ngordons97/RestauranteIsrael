package ec.edu.uisrael.apiorders.controller;

import ec.edu.uisrael.constants.Constants;
import ec.edu.uisrael.constants.RC;
import ec.edu.uisrael.apiorders.data.Order;
import ec.edu.uisrael.apiorders.data.Storage;
import ec.edu.uisrael.dto.GenericRequest;
import ec.edu.uisrael.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1")
public class OrderController {
    private Storage dataStore = Storage.getInstance();
    GenericResponse response;

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
        if (dataStore.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND,null),HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }

    @PostMapping("/order")
    public ResponseEntity<?> newOrder(@RequestBody Order order) {
        GenericResponse response;
        String id = UUID.randomUUID().toString();
        order.setOrderId(id);
        order.setState(Constants.STATE_PENDING);
        dataStore.addElement(id, order);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.CREATED,dataStore.getDataByKey(id)));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody Order order) {
        if (dataStore.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND,null),HttpStatus.NOT_FOUND);
        }
        dataStore.updateElement(id, order);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }

    @PatchMapping("/order/{id}")
    public ResponseEntity<?> changeStatusOrder(@PathVariable String id, @RequestBody GenericRequest request) {
        if (dataStore.getDataByKey(id) == null) {
            return ResponseEntity.ok().body(GenericResponse.Make(RC.NOT_FOUND,null));
        }
        if (request.getKey().equals(Constants.CHANGE_STATE)) {
            dataStore.getDataByKey(id).setState((String) request.getData());
        }
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }
}
