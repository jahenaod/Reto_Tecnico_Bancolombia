package co.com.bancolombia.model.retotecnicobancolombia.gateways;

import co.com.bancolombia.model.retotecnicobancolombia.RetoTecnicoBancolombia;

public interface RetoTecnicoBancolombiaRepository {
    RetoTecnicoBancolombia postCountryData(double area, long population);
}
