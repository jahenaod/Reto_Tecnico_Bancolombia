package co.com.bancolombia.model.reto_tecnico_bancolombia;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Reto_tecnico_bancolombia {
    private long population;
    private double area;
}
