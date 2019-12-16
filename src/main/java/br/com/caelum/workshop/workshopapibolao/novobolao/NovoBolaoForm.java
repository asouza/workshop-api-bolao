package br.com.caelum.workshop.workshopapibolao.novobolao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.caelum.workshop.workshopapibolao.novocampeonato.Campeonato;
import br.com.caelum.workshop.workshopapibolao.novocampeonato.CampeonatoRepository;
import br.com.caelum.workshop.workshopapibolao.shared.security.SystemUser;

public class NovoBolaoForm {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Long campeonatoId;
	
	@Size(min = 1)
	private List<@Email String> emails = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCampeonatoId() {
		return campeonatoId;
	}

	public void setCampeonatoId(Long campeonatoId) {
		this.campeonatoId = campeonatoId;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public Bolao toModel(CampeonatoRepository campeonatoRepository, SystemUser systemUser) {
		Campeonato campeonato = campeonatoRepository.findById(campeonatoId).get();
		return new Bolao(campeonato, systemUser, nome, emails);
	}
}
