package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.entities.CountryEntity;
import co.com.bancolombia.model.retotecnicobancolombia.RetoTecnicoBancolombia;
import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;

public interface JPARepository extends CrudRepository<CountryEntity, Integer> {
}
