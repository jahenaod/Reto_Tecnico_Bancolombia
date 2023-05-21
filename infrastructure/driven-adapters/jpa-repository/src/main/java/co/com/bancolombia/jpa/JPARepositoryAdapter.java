package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.entities.CountryEntity;
import co.com.bancolombia.model.retotecnicobancolombia.RetoTecnicoBancolombia;
import co.com.bancolombia.model.retotecnicobancolombia.gateways.RetoTecnicoBancolombiaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPARepositoryAdapter implements RetoTecnicoBancolombiaRepository {

    private final JPARepository repository;
    private final ObjectMapper mapper;

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RetoTecnicoBancolombia postCountryData(String name, double area, long population) {
        try {
            CountryEntity entity = new CountryEntity();
            entity.setArea(area);
            entity.setPopulation(population);
            entity.setName(name);

            CountryEntity savedEntity = repository.save(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

