package ec.edu.uisrael.apiperson.controller;

import ec.edu.uisrael.apiperson.data.FakeData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "v1")
public class PersonController {
    private Storage dataStore = Storage.getInstance();
    GenericResponse response;

    @GetMapping("")
    public String hi() {
        return "HI";
    }

    @GetMapping("/person")
    public ResponseEntity<?> getOrders() {
        return new ResponseEntity<>(dataStore.getData(), HttpStatus.OK);
    }



    @GetMapping("person")
    public ResponseEntity<?> getPerson() {
        FakeData fakeData = FakeData.getInstance();
        fakeData.getData();
        return new ResponseEntity<>(fakeData.getData(), HttpStatus.OK);
    }
}
