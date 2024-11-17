package ec.edu.uisrael.apigateway.controller;

import ec.edu.uisrael.apigateway.service.OrderService;
import ec.edu.uisrael.dto.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/order")
public class GatewayOrderController {
    @Autowired
    OrderService orderService;
    Logger log = LogManager.getLogger(GatewayOrderController.class);

    @GetMapping("/hc")
    public ResponseEntity<?> hi(){
        ResponseEntity response = new ResponseEntity<>("HI", HttpStatus.OK);
        return response;
    }

    @GetMapping
    public ResponseEntity<?> getOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        log.info("LLEGA CONTROLADOR:{}",id);
        return orderService.getOrder(id);
    }
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        log.info("LLEGA CONTROLADOR:{}",order.toString());
        return orderService.addOrder(order);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id,@RequestBody Order order){
        log.info("LLEGA CONTROLADOR:{}",order.toString());
        return orderService.updateOrder(id,order);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> pacthOrder(@PathVariable String id,@RequestBody Order order){
        log.info("LLEGA CONTROLADOR:{}",order.toString());
        return orderService.patchOrder(id,order);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        log.info("LLEGA CONTROLADOR:{}",id);
        return orderService.deleteOrder(id);
    }
}
