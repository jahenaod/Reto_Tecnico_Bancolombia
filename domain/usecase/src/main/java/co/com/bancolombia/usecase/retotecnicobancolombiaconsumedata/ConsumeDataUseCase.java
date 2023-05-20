package co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ConsumeDataUseCase {

    private final ConsumeDataRepository consumeDataRepository;

    public ConsumeData getDataCountry(String name) throws CountryNotFoundException {
        return consumeDataRepository.getDataCountry(name);
    }
}

