package co.com.bancolombia.consumer;

import co.com.bancolombia.model.retotecnicobancolombia.gateways.RetoTecnicoBancolombiaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RestConsumer  {

    @Value("${adapter.restconsumer.url}")
    private String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;


    public ObjectResponse getRequest() throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        return mapper.readValue(client.newCall(request).execute().body().string(), ObjectResponse.class);
    }

}
