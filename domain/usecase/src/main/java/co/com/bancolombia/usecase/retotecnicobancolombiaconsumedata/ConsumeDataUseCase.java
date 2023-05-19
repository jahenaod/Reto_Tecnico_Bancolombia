package co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ConsumeDataUseCase {

    private final ConsumeDataRepository consumeDataRepository;

    public ConsumeData getDataCountry(String name) throws IOException {
        return consumeDataRepository.getDataCountry(name);
    }
}
