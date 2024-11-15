package ec.edu.uisrael.apiorders.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private String orderId;
    private Product product;
    private Person client;
    private String state;
}
