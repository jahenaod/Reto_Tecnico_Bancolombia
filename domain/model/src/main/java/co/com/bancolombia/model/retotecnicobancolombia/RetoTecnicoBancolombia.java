package co.com.bancolombia.model.retotecnicobancolombia;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RetoTecnicoBancolombia {
    private double area;
     private long population;
     private String name;
}
