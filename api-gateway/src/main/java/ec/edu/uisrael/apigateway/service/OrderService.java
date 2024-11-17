package ec.edu.uisrael.apigateway.service;

import ec.edu.uisrael.apigateway.constant.Constants;
import ec.edu.uisrael.dto.GenericRequest;
import ec.edu.uisrael.dto.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {
    private static final Logger log = LogManager.getLogger(OrderService.class);
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResponseEntity<?> getAllOrder() {
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(Constants.URl_API_ORDER + "/order", String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> getOrder(String id) {
        ResponseEntity<String> response = null;
        String url = Constants.URl_API_ORDER + "/order/" + id;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> addOrder(Order order) {
        ResponseEntity<String> response = null;
        log.info("Add Order: {}", order);
        HttpEntity<Order> request = new HttpEntity<>(order);
        log.info("Add Order HttpEntity: {}", request);
        try {
            response = restTemplate.postForEntity(Constants.URl_API_ORDER + "/order", request, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> updateOrder(String id, Order order) {
        ResponseEntity<String> response = null;
        String url = Constants.URl_API_ORDER + "/order/" + id;
        HttpEntity<Order> request = new HttpEntity<>(order);
        try {
            log.info("Update Order HttpEntity: {} - {}",url , request);
            response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        } catch (Exception e) {
            log.error("ERROR Update : {} - {}",url , e.getMessage());
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> patchOrder(String id, Order order) {
        String url = Constants.URl_API_ORDER + "/order";
        try {
            GenericRequest entity = GenericRequest.builder()
                    .key(ec.edu.uisrael.constants.Constants.CHANGE_STATE)
                    .data(order.getState())
                    .build();
            WebClient webClient = webClientBuilder.baseUrl(url).build();
            // Enviar la solicitud PATCH y obtener la respuesta
            String response = webClient.patch()
                    .uri("/{id}", id)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .bodyValue(entity)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            // Si la respuesta no es nula o vacía, retornamos una respuesta exitosa
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            }

        } catch (Exception e) {
            log.error("ERROR parcial Update : {} - {}", url, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteOrder(String id) {
        ResponseEntity<String> response = null;
        try {
            String url = Constants.URl_API_ORDER + "/order/" + id;
            response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
