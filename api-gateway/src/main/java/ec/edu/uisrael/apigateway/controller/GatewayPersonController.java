package ec.edu.uisrael.apigateway.controller;

import ec.edu.uisrael.apigateway.service.PersonService;
import ec.edu.uisrael.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping(path = "/v1/person")
public class GatewayPersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/hc")
    public ResponseEntity<?> hi(){
        ResponseEntity response = new ResponseEntity<>("HI", HttpStatus.OK);
        return response;
    }

    @GetMapping
    public ResponseEntity<?> getPerson(){
        return personService.getAllPerson();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id){
        return personService.getPerson(id);
    }
    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }
}
