package br.com.allan.repositories;

import br.com.allan.models.Convidado;
import br.com.allan.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository <Convidado, String>{

    Iterable<Convidado> findByEvento(Evento evento);

    Convidado findByRg(String rg);

}