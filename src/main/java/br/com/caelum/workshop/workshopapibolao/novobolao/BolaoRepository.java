package br.com.caelum.workshop.workshopapibolao.novobolao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BolaoRepository extends CrudRepository<Bolao, Long>{

}
