package co.com.bancolombia.api;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata.ConsumeDataUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ApiRestTest {

    @Test
    public void getDataCountry_ValidName_ReturnsData() throws IOException, CountryNotFoundException {
        // Arrange
        String name = "Colombia";
        long areaTest = (long) 1141748.0;
        long populationTest = 50882884;
        ConsumeDataUseCase consumeDataUseCase = Mockito.mock(ConsumeDataUseCase.class);
        ConsumeData expectedData = new ConsumeData(name, areaTest, populationTest);

        when(consumeDataUseCase.getDataCountry(anyString())).thenReturn(expectedData);

        ApiRest apiRest = new ApiRest(consumeDataUseCase);

        // Act
        ResponseEntity response = apiRest.getDataCountry(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(expectedData, response.getBody());
        verify(consumeDataUseCase, times(1)).getDataCountry(name);
    }

    @Test
    public void getDataCountry_InvalidName_ReturnsNotFound() throws IOException, CountryNotFoundException {
        // Arrange
        String name = "NonexistentCountry";
        ConsumeDataUseCase consumeDataUseCase = Mockito.mock(ConsumeDataUseCase.class);
        when(consumeDataUseCase.getDataCountry(anyString())).thenThrow(new CountryNotFoundException("Country not found"));

        ApiRest apiRest = new ApiRest(consumeDataUseCase);

        // Act
        ResponseEntity response = apiRest.getDataCountry(name);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Country not found", response.getBody());
        verify(consumeDataUseCase, times(1)).getDataCountry(name);
    }
}
