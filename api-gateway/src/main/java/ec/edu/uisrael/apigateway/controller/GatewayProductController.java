package ec.edu.uisrael.apigateway.controller;

import ec.edu.uisrael.apigateway.service.ProductService;
import ec.edu.uisrael.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/product")
public class GatewayProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/hc")
    public ResponseEntity<?> hi(){
        ResponseEntity response = new ResponseEntity<>("HI", HttpStatus.OK);
        return response;
    }

    @GetMapping
    public ResponseEntity<?> getProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        return productService.getProduct(id);
    }
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
