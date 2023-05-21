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
    public RetoTecnicoBancolombia postCountryData(double area, long population) {

        RetoTecnicoBancolombia entity = new RetoTecnicoBancolombia();
        entity.setArea(area);
        entity.setPopulation(population);

        CountryEntity savedEntity = repository.save(mapper.map(entity, CountryEntity.class));

        return mapper.map(savedEntity, RetoTecnicoBancolombia.class);
    }
}

