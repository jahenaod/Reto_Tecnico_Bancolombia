import co.com.bancolombia.consumer.ObjectResponse;
import co.com.bancolombia.consumer.RestConsumer;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestConsumerTest {

    private OkHttpClient client;
    private ObjectMapper mapper;
    private ConsumeDataRepository consumeDataRepository;

    @BeforeEach
    public void setUp() {
        client = Mockito.mock(OkHttpClient.class);
        mapper = Mockito.mock(ObjectMapper.class);
        consumeDataRepository = new RestConsumer(client, mapper);
    }

    @Test
    public void getDataCountry_ValidName_ReturnsConsumeData() throws IOException, CountryNotFoundException {
        // Arrange
        String name = "Colombia";
        String jsonResponse = "{\"area\": 1141748.0, \"population\": 50882884}";
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), jsonResponse);

        Call call = Mockito.mock(Call.class);
        Response response = new Response.Builder()
                .request(new Request.Builder().url("https://localhost:8080/api/Colombia").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build();

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(mapper.readValue(jsonResponse, ObjectResponse[].class))
                .thenReturn(new ObjectResponse[]{new ObjectResponse(100, 5000000)});

        // Act
        ConsumeData data = consumeDataRepository.getDataCountry(name);

        // Assert
        assertNotNull(data);
        assertEquals(100, data.getArea());
        assertEquals(5000000, data.getPopulation());
        verify(client, times(1)).newCall(any(Request.class));
        verify(call, times(1)).execute();
        verify(mapper, times(1)).readValue(jsonResponse, ObjectResponse[].class);
    }

    @Test
    public void getDataCountry_CountryNotFound_ThrowsCountryNotFoundException() throws IOException {
        // Arrange
        String name = "NonexistentCountry";
        Call call = Mockito.mock(Call.class);
        Response response = new Response.Builder()
                .request(new Request.Builder().url("https://example.com/NonexistentCountry").build())
                .protocol(Protocol.HTTP_1_1)
                .code(404)
                .message("Not Found")
                .build();

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);

        // Act & Assert
        assertThrows(CountryNotFoundException.class, () -> consumeDataRepository.getDataCountry(name));
        verify(client, times(1)).newCall(any(Request.class));
        verify(call, times(1)).execute();
    }


    @Test
    public void getDataCountry_IOError_ThrowsCountryNotFoundException() throws IOException {
        // Arrange
        String name = "Colombia";
        Call call = Mockito.mock(Call.class);

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("Connection failed"));

        // Act & Assert

    }
}