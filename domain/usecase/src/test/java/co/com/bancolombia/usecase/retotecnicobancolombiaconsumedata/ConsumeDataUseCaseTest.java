package co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata;

import co.com.bancolombia.model.retotecnicobancolombia.gateways.RetoTecnicoBancolombiaRepository;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ConsumeDataUseCaseTest {

    @Mock
    private ConsumeDataRepository consumeDataRepository;

    @Mock
    private RetoTecnicoBancolombiaRepository retoTecnicoBancolombiaRepository;

    private ConsumeDataUseCase consumeDataUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consumeDataUseCase = new ConsumeDataUseCase(consumeDataRepository, retoTecnicoBancolombiaRepository);
    }

    @Test
    void testGetDataCountry() throws CountryNotFoundException {
        // Given
        String countryName = "Colombia";
        long areaTest = (long) 1141748.0;
        long populationTest = 50882884;
        ConsumeData consumeData = new ConsumeData(countryName, populationTest, areaTest);

        when(consumeDataRepository.getDataCountry(countryName)).thenReturn(consumeData);

        // When
        ConsumeData result = consumeDataUseCase.getDataCountry(countryName);

        // Then
        verify(consumeDataRepository, times(1)).getDataCountry(countryName);
        verify(retoTecnicoBancolombiaRepository, times(1))
                .postCountryData(countryName, consumeData.getArea(), consumeData.getPopulation());

    }


    @Test
    void testGetDataCountry_CountryNotFoundException() throws CountryNotFoundException {
        // Given
        String countryName = "InvalidCountryName";

        // When
        when(consumeDataRepository.getDataCountry(countryName)).thenThrow(CountryNotFoundException.class);

        // Then
        assertThrows(CountryNotFoundException.class, () -> consumeDataUseCase.getDataCountry(countryName));
        verify(consumeDataRepository, times(1)).getDataCountry(countryName);
        verifyNoInteractions(retoTecnicoBancolombiaRepository);   verifyNoInteractions(retoTecnicoBancolombiaRepository);
    }

}
