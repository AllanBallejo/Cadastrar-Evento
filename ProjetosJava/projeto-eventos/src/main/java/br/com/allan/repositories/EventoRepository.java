package br.com.allan.repositories;

import br.com.allan.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, String> {

    Evento findByCodigo (long codigo);

}