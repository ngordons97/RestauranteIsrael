package ec.edu.uisrael.apigateway.service;

import ec.edu.uisrael.apigateway.constant.Constants;
import ec.edu.uisrael.dto.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {

    public ResponseEntity<?> getAllPerson() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(Constants.URl_API_PERSON + "/person", String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> getPerson(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        String url = Constants.URl_API_PERSON + "/person/" + id;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> addPerson(Person person) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        HttpEntity<Person> request = new HttpEntity<>(person);
        try {
            response = restTemplate.postForEntity(Constants.URl_API_PERSON + "/person",request, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> updatePerson(Person person) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URl_API_PERSON + "/person/" + person.getDni();
        ResponseEntity<String> response = null;
        HttpEntity<Person> request = new HttpEntity<>(person);
        try {
            response = restTemplate.exchange(url, HttpMethod.PUT,request,String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    public ResponseEntity<?> deletePerson(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URl_API_PERSON + "/person/" + id;
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(url,HttpMethod.DELETE,null, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }
}
