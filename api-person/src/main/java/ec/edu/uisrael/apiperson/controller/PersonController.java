package ec.edu.uisrael.apiperson.controller;

import ec.edu.uisrael.apiperson.data.FakeData;
import ec.edu.uisrael.apiperson.data.Person;
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
    private Storage dataStore = Storage.getInstance();
    GenericResponse response;

    @GetMapping("")
    public String hi() {
        return "HI";
    }

    @GetMapping("/person")
    public ResponseEntity<?> getPersons() {
        return new ResponseEntity<>(dataStore.getData(), HttpStatus.OK);
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
        
        person.setPersonId(id);
        person.setState(Constants.STATE_PENDING);
        dataStore.addElement(id, person);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.CREATED,dataStore.getDataByKey(id)));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable String id, @RequestBody Order order) {
        if (dataStore.getDataByKey(id) == null) {
            return new ResponseEntity<>(GenericResponse.Make(RC.NOT_FOUND,null),HttpStatus.NOT_FOUND);
        }
        dataStore.updateElement(id, person);
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }

    @PatchMapping("/person/{id}")
    public ResponseEntity<?> changeStatusPerson(@PathVariable String id, @RequestBody GenericRequest request) {
        if (dataStore.getDataByKey(id) == null) {
            return ResponseEntity.ok().body(GenericResponse.Make(RC.NOT_FOUND,null));
        }
        if (request.getKey().equals(Constants.CHANGE_STATE)) {
            dataStore.getDataByKey(id).setState((String) request.getData());
        }
        return ResponseEntity.ok().body(GenericResponse.Make(RC.OK,dataStore.getDataByKey(id)));
    }

    @GetMapping("person")
    public ResponseEntity<?> getPerson() {
        FakeData fakeData = FakeData.getInstance();
        fakeData.getData();
        return new ResponseEntity<>(fakeData.getData(), HttpStatus.OK);
    }
}
