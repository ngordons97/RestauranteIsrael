package ec.edu.uisrael.apiproducts.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String id;
    private String type;
    private String name;
    private Double cost;
    private int stock;
}
