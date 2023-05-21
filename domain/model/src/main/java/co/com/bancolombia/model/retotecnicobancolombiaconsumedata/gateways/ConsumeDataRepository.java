package co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways;

import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;

public interface ConsumeDataRepository {
    ConsumeData getDataCountry(String name) throws CountryNotFoundException;
}
