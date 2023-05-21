package co.com.bancolombia.model.retotecnicobancolombiaconsumedata;
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
public class ConsumeData {
    String name;

    private double area;
    private Long population;
}
