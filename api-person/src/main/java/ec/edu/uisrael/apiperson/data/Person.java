package ec.edu.uisrael.apiperson.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private String firstName;
    private String lastName;
    private String dni;
}
