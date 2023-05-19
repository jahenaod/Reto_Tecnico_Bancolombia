package co.com.bancolombia.model.retotecnicobancolombia;
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
public class RetoTecnicoBancolombia {
    private double area;
    private Long population;
}
