package ec.edu.uisrael.apiproducts.controller;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import ec.edu.uisrael.apiproducts.data.FakeData;
import ec.edu.uisrael.apiproducts.data.Product;
import lombok.experimental.PackagePrivate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.RequestContextFilter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(path = "/v1")
public class ProductController {

    private final RequestContextFilter requestContextFilter;
    private FakeData fakeData = FakeData.getInstance();

    public ProductController(RequestContextFilter requestContextFilter) {
        this.requestContextFilter = requestContextFilter;
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct() {
        return new ResponseEntity<>(fakeData.getData(), HttpStatus.OK);
    }

    @GetMapping("/product/{code}")
    public ResponseEntity<?> getProductById(@PathVariable String code) {
        Product product = fakeData.getDataByKey(code);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }

        fakeData.addData(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateData(@PathVariable String id, @RequestBody Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fakeData.updateData(id, product);
        return new ResponseEntity<>("Producto actulizado correctamente",HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> updateDataById(@PathVariable String id, @RequestBody Product product) {
        if (product.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        fakeData.updateDataById(id, product);
        return new ResponseEntity<>("Producto actualizado correctamente",HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        fakeData.removeData(id); // Método para eliminar datos en `fakeData`

        return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.NO_CONTENT); // Código 204 - Eliminación exitosa
    }


}
