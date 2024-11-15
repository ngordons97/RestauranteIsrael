package ec.edu.uisrael.apigateway.controller;

import ec.edu.uisrael.apigateway.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping(path = "/v1")
public class GatewayController {
    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<?> hi(){
        ResponseEntity response = new ResponseEntity<>("HI", HttpStatus.OK);
        return response;
    }
    @GetMapping("/person")
    public ResponseEntity<?> getPerson(){
//        ResponseEntity person = personService.getPerson();
//        ResponseEntity response = new ResponseEntity<>(person, HttpStatus.OK);
        return personService.getPerson();
    }
}
