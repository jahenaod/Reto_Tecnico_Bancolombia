package co.com.bancolombia.api;
import co.com.bancolombia.model.retotecnicobancolombia.RetoTecnicoBancolombia;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.usecase.retotecnicobancolombia.RetoTecnicoBancolombiaUseCase;
import co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata.ConsumeDataUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final ConsumeDataUseCase retoTecnicoBancolombiaConsumeDataUseCase;
    private final RetoTecnicoBancolombiaUseCase retoTecnicoBancolombiaUseCase;
    @GetMapping(path = "/{name}")
    public ResponseEntity getDataCountry(@PathVariable String name) throws IOException {
       try {
           ConsumeData data = retoTecnicoBancolombiaConsumeDataUseCase.getDataCountry(name);

           RetoTecnicoBancolombia savedData = retoTecnicoBancolombiaUseCase.postCountryData(data.getArea(), data.getPopulation());
           return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(retoTecnicoBancolombiaConsumeDataUseCase.getDataCountry(name));
       }catch (CountryNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }

    @PostMapping(path = "/data")
    public ResponseEntity postCountryData(@RequestBody Map<String, Object> requestData){
        long area = ((Number) requestData.get("area")).longValue();
        double population = (double) requestData.get("population");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(retoTecnicoBancolombiaUseCase.postCountryData(population, area));
    }


}
