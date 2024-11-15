package ec.edu.uisrael.apiproducts.controller;

import ec.edu.uisrael.apiproducts.data.FakeData;
import ec.edu.uisrael.apiproducts.data.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class ProductController {

    @GetMapping("/product")
    public ResponseEntity<?> getProduct() {
        FakeData fakeData = FakeData.getInstance();
        return new ResponseEntity<>(fakeData.getData(), HttpStatus.OK);
    }
    @GetMapping("/product/{code}")
    public ResponseEntity<?> getProductById(@PathVariable String code) {
        FakeData fakeData = FakeData.getInstance();
        Product product = fakeData.getDataByKey(code);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
