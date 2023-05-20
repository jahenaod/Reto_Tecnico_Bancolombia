package co.com.bancolombia.consumer.exception;
import lombok.*;
import java.time.ZonedDateTime;
@AllArgsConstructor
@Getter
public class ApiException {
    private final String message;
    private final ZonedDateTime timestamp;
}
