package co.com.bancolombia.model.retotecnicobancolombiaconsumedata;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConsumeData {
    String name;

    private long area;
    private long population;
}
