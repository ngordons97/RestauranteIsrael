package ec.edu.uisrael.apigateway.service;

import ec.edu.uisrael.apigateway.constant.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {
    public PersonService() {
    }

    public ResponseEntity<?> getPerson() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(Constants.URl_API_PERSON + "/person", String.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response !=null && !response.getStatusCode().equals(HttpStatus.OK)) {
            return new ResponseEntity<>("ERROR", HttpStatus.OK);
        }
        return response;
    }
}
