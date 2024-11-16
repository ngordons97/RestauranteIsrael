package ec.edu.uisrael.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericRequest {
    private String code;
    private String key;
    private Object data;
}
