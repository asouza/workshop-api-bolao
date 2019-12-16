package br.com.caelum.workshop.workshopapibolao.novobolao;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.workshop.workshopapibolao.novocampeonato.Campeonato;
import br.com.caelum.workshop.workshopapibolao.novocampeonato.CampeonatoRepository;
import br.com.caelum.workshop.workshopapibolao.shared.security.SystemUserDetails;

@RestController
public class BolaoController {
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	@Autowired
	private BolaoRepository bolaoRepository;
	@Autowired
	private Emails emails;

	/*
	 * precisa ter um form que represente o novo bolao
	 * preicamos fazer as validacoes que sao basicas
	 * lista de emails tem que ser maior que zero
	 * todo email dentro da lista de email tem que ser valido
	 * transformar para um bolao
	 * gerar os invites com data de expiracao para cada email
	 * salvar tudo
	 * mandar os invites por email com o link de aceitacao
	 * 
	 */
	@Transactional
	@PostMapping(value = "/api/bolao")
	public String postMethodName(@RequestBody @Valid NovoBolaoForm form, @AuthenticationPrincipal SystemUserDetails currentUser) {
		Bolao novoBolao = form.toModel(campeonatoRepository, currentUser.getSystemUser());
		
		bolaoRepository.save(novoBolao);
		emails.enviaConvites(novoBolao);
		return "pombo sujo";
	}

}
