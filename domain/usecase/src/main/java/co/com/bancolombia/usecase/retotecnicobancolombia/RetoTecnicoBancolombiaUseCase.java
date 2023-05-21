package co.com.bancolombia.usecase.retotecnicobancolombia;

import co.com.bancolombia.model.retotecnicobancolombia.RetoTecnicoBancolombia;
import co.com.bancolombia.model.retotecnicobancolombia.gateways.RetoTecnicoBancolombiaRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class RetoTecnicoBancolombiaUseCase {
    private final RetoTecnicoBancolombiaRepository retoTecnicoBancolombiaRepository;

    public RetoTecnicoBancolombia postCountryData(double area, Long population){
        return retoTecnicoBancolombiaRepository.postCountryData(area,population);
    }


}
