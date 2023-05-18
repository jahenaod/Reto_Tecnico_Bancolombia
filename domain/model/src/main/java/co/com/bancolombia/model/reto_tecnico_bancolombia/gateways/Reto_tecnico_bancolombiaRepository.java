package co.com.bancolombia.model.reto_tecnico_bancolombia.gateways;

import co.com.bancolombia.model.reto_tecnico_bancolombia.Reto_tecnico_bancolombia;
import reactor.core.publisher.Mono;

public interface Reto_tecnico_bancolombiaRepository {
    Mono<Reto_tecnico_bancolombia> getObjectByName(String name);
    Mono<Reto_tecnico_bancolombia> getObjectsAll();
}
