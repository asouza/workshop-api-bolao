package br.com.caelum.workshop.workshopapibolao.novobolao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import br.com.caelum.workshop.workshopapibolao.novocampeonato.Campeonato;
import br.com.caelum.workshop.workshopapibolao.shared.security.SystemUser;
@Entity
public class Bolao implements TemConvites{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@NotNull
	@ManyToOne
	private Campeonato campeonato;
	@NotNull
	@PastOrPresent
	private LocalDateTime instanteCriacao = LocalDateTime.now();
 	@NotNull
 	@ManyToOne
	private SystemUser owner;
	private @NotBlank String nome;
	@OneToMany(mappedBy = "bolao", cascade = CascadeType.PERSIST)
	private Set<Convite> convites = new HashSet<>();

	@Deprecated
	public Bolao() {
	}
	
	public Bolao(@NotNull Campeonato campeonato, @NotNull SystemUser systemUser, @NotBlank String nome,
			@Size(min = 1) List<@Email String> emails) {
		this.campeonato = campeonato;
		this.owner = systemUser;
		this.nome = nome;
		convites.addAll(emails.stream().map(email ->{
			return new Convite(this, 10, email);
		}).collect(Collectors.toSet()));
	}

	@Override
	public Collection<Convite> getConvites() {
		
		return Collections.unmodifiableSet(convites);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bolao other = (Bolao) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
