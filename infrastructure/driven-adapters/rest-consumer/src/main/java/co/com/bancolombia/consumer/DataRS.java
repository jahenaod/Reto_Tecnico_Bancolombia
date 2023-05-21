package co.com.bancolombia.consumer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude()
public class DataRS {
    private long population;
    private long area;
}
