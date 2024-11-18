package ec.edu.uisrael.apiperson.controller;

import ec.edu.uisrael.apiperson.data.FakeData;
import ec.edu.uisrael.constants.Constants;
import ec.edu.uisrael.constants.RC;
import ec.edu.uisrael.dto.GenericRequest;
import ec.edu.uisrael.dto.GenericResponse;
import ec.edu.uisrael.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "v1")
public class PersonController {
    private FakeData dataStore = FakeData.getInstance();

    @GetMapping("")
    public String hi() {
        return "HI";
    }

    @GetMapping("/person")
    public ResponseEntity<?> getPersons() {

        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getData()));
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id) {
        if (dataStore.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND,null),HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }

    @PostMapping("/person")
    public ResponseEntity<?> newPerson(@RequestBody Person person) {
        GenericResponse response;
        String id = UUID.randomUUID().toString();
        dataStore.addElement(id, person);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.CREATED,dataStore.getDataByKey(id)));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable String id, @RequestBody Person person) {
        if (dataStore.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND,null),HttpStatus.NOT_FOUND);
        }
        dataStore.updateElement(id, person);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }
    
    @PatchMapping("/person/{id}")
    public ResponseEntity<?> changeStatusPerson(@PathVariable String id, @RequestBody  Person person) {
        if (dataStore.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND,null),HttpStatus.NOT_FOUND);
        }
        dataStore.updateElement(id, person);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }
}
