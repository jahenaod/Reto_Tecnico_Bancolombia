package co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata;

import co.com.bancolombia.model.retotecnicobancolombia.gateways.RetoTecnicoBancolombiaRepository;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ConsumeDataUseCase {

    private final ConsumeDataRepository consumeDataRepository;

    private final RetoTecnicoBancolombiaRepository retoTecnicoBancolombiaRepository;
    public ConsumeData getDataCountry(String name) throws CountryNotFoundException {

        ConsumeData data = consumeDataRepository.getDataCountry(name);

        retoTecnicoBancolombiaRepository.postCountryData(name, data.getArea(), data.getPopulation());

        return data;
    }


}

