package co.com.bancolombia.consumer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjectResponse {

    private String status;
    private long population;
    private double area;

}