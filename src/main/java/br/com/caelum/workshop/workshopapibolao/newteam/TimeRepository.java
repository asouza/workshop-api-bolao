package br.com.caelum.workshop.workshopapibolao.newteam;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends CrudRepository<Time, Long>{

	@Override
	 Collection<Time> findAllById(Iterable<Long> ids) ;
	
	
	
}

