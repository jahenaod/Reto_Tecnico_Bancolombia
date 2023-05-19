package co.com.bancolombia.consumer;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
            String strResponseMessaje = response.message();
            ObjectResponse res = mapper.readValue(response.body().string(), ObjectResponse.class);
            response.close();
            return strResponseMessaje;
        } catch (IOException e) {
            throw new IOException("An error occurred while processing the data.", e);
        }
    }

    @Override
    public ConsumeData getDataCountry(String name) throws IOException {
        //ObjectResponse response = getResponse();
        ObjectResponse response = mapper.readValue(getResponse(name), ObjectResponse.class);
        //double area = response.getArea();
        //Long population = response.getPopulation();

        return ConsumeData.builder().area(response.getArea())
                .population(response.getPopulation()).build();
    }
}
