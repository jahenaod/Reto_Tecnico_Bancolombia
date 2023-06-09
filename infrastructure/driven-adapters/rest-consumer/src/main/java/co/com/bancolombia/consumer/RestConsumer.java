package co.com.bancolombia.consumer;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
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

    public String getResponse(String name) throws IOException, CountryNotFoundException {
        try {
            Request request = new Request.Builder()
                    .url(url.concat(name))
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            if (response.code() == 404) {
                throw new CountryNotFoundException("Country not found");
            }
            return response.body().string();
        } catch (IOException e) {
            throw new IOException("An error occurred while processing the data.", e);
        }catch (CountryNotFoundException e) {
            throw e;
        }
    }

    @Override
    public ConsumeData getDataCountry(String name) throws CountryNotFoundException {
        try {
            ObjectResponse[] response = ObjectResponse.fromJson(getResponse(name));
            return ConsumeData.builder().area(response[0].getArea())
                    .population(response[0].getPopulation()).build();
        } catch (IOException e) {
            throw new CountryNotFoundException("Error retrieving country data.");
        }catch (CountryNotFoundException e) {
            throw e;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

