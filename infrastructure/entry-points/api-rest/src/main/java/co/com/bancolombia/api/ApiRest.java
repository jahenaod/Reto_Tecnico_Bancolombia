package co.com.bancolombia.api;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.usecase.retotecnicobancolombiaconsumedata.ConsumeDataUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final ConsumeDataUseCase retoTecnicoBancolombiaConsumeDataUseCase;

    @GetMapping(path = "/{name}")
    public ConsumeData getDataCountry(@PathVariable String name) throws IOException {
        return retoTecnicoBancolombiaConsumeDataUseCase.getDataCountry(name);
    }


}
