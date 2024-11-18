package ec.edu.uisrael.apiproducts.controller;

import ec.edu.uisrael.apiproducts.data.FakeData;
import ec.edu.uisrael.constants.RC;
import ec.edu.uisrael.dto.GenericResponse;
import ec.edu.uisrael.dto.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.RequestContextFilter;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1")
public class ProductController {

    private FakeData fakeData = FakeData.getInstance();

    @GetMapping("/product")
    public ResponseEntity<?> getProduct() {
        return new ResponseEntity<>(GenericResponse.Make(RC.OK, fakeData.getData()), HttpStatus.OK);
    }

    @GetMapping("/product/{code}")
    public ResponseEntity<?> getProductById(@PathVariable String code) {
        Product product = fakeData.getDataByKey(code);
        if (product == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(GenericResponse.Make(RC.OK, product), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        fakeData.addData(product);
        return new ResponseEntity<>(GenericResponse.Make(RC.CREATED, fakeData.getDataByKey(product.getId())), HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateData(@PathVariable String id, @RequestBody Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        fakeData.updateData(id, product);
        return new ResponseEntity<>("Producto actulizado correctamente", HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> updateDataById(@PathVariable String id, @RequestBody Product product) {
        if (fakeData.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        Product tmp = fakeData.getDataByKey(id);
        fakeData.getDataByKey(id).setId(product.getId() != null ? product.getId() : tmp.getId());
        fakeData.getDataByKey(id).setType(product.getType() != null ? product.getType() : tmp.getType());
        fakeData.getDataByKey(id).setName(product.getName() != null ? product.getName() : tmp.getName());
        fakeData.getDataByKey(id).setCost(product.getCost() != null ? product.getCost() : tmp.getCost());
        fakeData.getDataByKey(id).setStock(product.getStock() != tmp.getStock() ? product.getStock() : tmp.getStock());

        return new ResponseEntity<>(GenericResponse.Make(RC.OK, null), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        fakeData.removeData(id); // Método para eliminar datos en `fakeData`

        return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.NO_CONTENT); // Código 204 - Eliminación exitosa
    }


}
