package br.com.caelum.workshop.workshopapibolao.novocampeonato;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.workshop.workshopapibolao.newteam.TimeRepository;

@RestController
public class CampeonatoController {
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	
	@Autowired
	private TimeRepository timeRepository;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new NumeroDeTimesEQuantidadeSelecionadaTemQueSerIgualValidator());
	}
	
	@PostMapping(value = "/api/campeonato")
	@Transactional
	public String save(@RequestBody @Valid NovoCampeonatoForm form) {
		Campeonato novoCampeonato = form.toModel(timeRepository);
		
		campeonatoRepository.save(novoCampeonato);
		return novoCampeonato.toString();
	}

	
}
