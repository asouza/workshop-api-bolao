package br.com.caelum.workshop.workshopapibolao.novocampeonato;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.caelum.workshop.workshopapibolao.newteam.Time;

@Entity
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private @NotBlank String nome;
	private @NotNull @FutureOrPresent LocalDate dataComeco;
	private @Min(1) int numeroTime;

	@ManyToMany
	private Set<Time> times = new HashSet<>();

	public Campeonato(@NotBlank String nome, @NotNull @FutureOrPresent LocalDate dataComeco,
			@Min(1) int numeroTime, Collection<Time> times) {
				this.nome = nome;
				this.dataComeco = dataComeco;
				this.numeroTime = numeroTime;
				this.times.addAll(times);
	}
	
	@Deprecated
	public Campeonato() {
	}

	@Override
	public String toString() {
		return "Campeonato [nome=" + nome + ", dataComeco=" + dataComeco + ", numeroTime=" + numeroTime + "]";
	}

	
	
}
