package br.com.caelum.workshop.workshopapibolao.newteam;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

	/*
	 * vou criar o metodo que recebe os dados
	 * vou mapear os dados para alguma classe de formulario
	 * vou pegar o formulario e transformar para um objeto de dominio
	 * vou salvar o objeto de dominio
	 * vou retornar um status para o cliente que chamou meu endpoint
	 */
	
	@Autowired
	private TimeRepository timeRepository;
	
	@PostMapping(value = "/api/team")
	@Transactional
	public String save(@RequestBody @Valid NovoTimeForm form) {
		Time novoTime = form.toModel();
		
		timeRepository.save(novoTime);
		return "time salvo";
	}

}
