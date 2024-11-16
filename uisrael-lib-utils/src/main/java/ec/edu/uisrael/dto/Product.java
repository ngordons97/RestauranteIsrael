package ec.edu.uisrael.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String type;
    private String name;
    private Double cost;
    private int stock;
}
