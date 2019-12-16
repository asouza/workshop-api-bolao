package br.com.caelum.workshop.workshopapibolao.newteam;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
		
	@Column(unique = true)
	private @NotBlank String nome;
	private @NotNull @Past LocalDate dataFundacao;

	@Deprecated
	public Time() {
		
	}
	
	public Time(@NotBlank String nome, @NotNull @Past LocalDate dataFundacao) {
		this.nome = nome;
		this.dataFundacao = dataFundacao;
		
	}

	@Override
	public String toString() {
		return "Time [nome=" + nome + ", dataFundacao=" + dataFundacao + "]";
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
		Time other = (Time) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	

}
