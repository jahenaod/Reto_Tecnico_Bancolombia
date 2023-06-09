package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.JPARepository;
import co.com.bancolombia.jpa.JPARepositoryAdapter;
import co.com.bancolombia.jpa.entities.CountryEntity;
import co.com.bancolombia.model.retotecnicobancolombia.RetoTecnicoBancolombia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

public class JPARepositoryAdapterTest {

    @Mock
    private JPARepository repository;
    private ObjectMapper mapper;

    private JPARepositoryAdapter repositoryAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        repositoryAdapter = new JPARepositoryAdapter(repository, mapper);
    }

    @Test
    public void testPostCountryData_SuccessfulSave() {
        String name = "CountryName";
        long area = (long)100.0;
        long population = 1000000;

        try {

            Mockito.when(repository.save(Mockito.any(CountryEntity.class))).thenReturn(CountryEntity
                    .builder().area(area).name(name).population(population).build());
        }catch (Exception e){
            e.printStackTrace();
        }

        RetoTecnicoBancolombia result = repositoryAdapter.postCountryData(name, area, population);

        Mockito.verify(repository).save(Mockito.argThat(entity ->
                entity.getName().equals(name)
                        && entity.getArea() == area
                        && entity.getPopulation() == population
        ));

        Assertions.assertNotNull(result);
    }
}
