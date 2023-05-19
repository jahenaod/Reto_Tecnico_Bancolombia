package co.com.bancolombia.consumer;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestConsumer implements ConsumeDataRepository {

    @Value("${adapter.restconsumer.url}")
    private String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public String getResponse(String name) throws IOException {
        try {
            Request request = new Request.Builder()
                    .url(url.concat(name))
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new IOException("An error occurred while processing the data.", e);
        }
    }

    @Override
    public ConsumeData getDataCountry(String name) throws IOException {

        Gson json = new Gson();
        ObjectResponse[] response = ObjectResponse.fromJson(getResponse(name));

        return ConsumeData.builder().area(response[0].getArea())
                .population(response[0].getPopulation()).build();
    }
}
