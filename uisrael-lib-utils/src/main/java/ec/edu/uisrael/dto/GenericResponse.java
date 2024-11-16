package ec.edu.uisrael.dto;

import ec.edu.uisrael.constants.RC;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {
    private int code;
    private String message;
    private Object data;

    public static GenericResponse Make(RC rc, Object data) {
        return  GenericResponse.builder().code(rc.getCode()).message(rc.getMessage()).data(data).build();
    }
}
