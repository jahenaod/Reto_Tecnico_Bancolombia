package co.com.bancolombia.consumer;

import com.google.gson.Gson;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjectResponse {

    private long population;
    private long area;

    public static ObjectResponse[] fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ObjectResponse[].class);
    }

}