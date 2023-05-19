package co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;

import java.io.IOException;

public interface ConsumeDataRepository {
    ConsumeData getDataCountry(String name) throws IOException;
}
