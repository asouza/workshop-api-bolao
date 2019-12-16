package br.com.caelum.workshop.workshopapibolao.novocampeonato;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends CrudRepository<Campeonato, Long>{

}
