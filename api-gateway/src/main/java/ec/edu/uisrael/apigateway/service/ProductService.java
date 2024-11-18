package ec.edu.uisrael.apigateway.service;

import ec.edu.uisrael.apigateway.constant.Constants;
import ec.edu.uisrael.dto.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    public ResponseEntity<?> getAllProduct() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(Constants.URl_API_PRODUCT + "/product", String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> getProduct(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        String url = Constants.URl_API_PRODUCT + "/product/" + id;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> addProduct(Product product) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        HttpEntity<Product> request = new HttpEntity<>(product);
        try {
            response = restTemplate.postForEntity(Constants.URl_API_PRODUCT + "/product",request, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> updateProduct(Product product) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URl_API_PRODUCT + "/product/" + product.getId();
        ResponseEntity<String> response = null;
        HttpEntity<Product> request = new HttpEntity<>(product);
        try {
            response = restTemplate.exchange(url, HttpMethod.PUT,request,String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    public ResponseEntity<?> deleteProduct(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URl_API_PRODUCT + "/product/" + id;
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(url,HttpMethod.DELETE,null, String.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }
}
