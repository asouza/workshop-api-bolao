package br.com.caelum.workshop.workshopapibolao.novobolao;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.util.Assert;
@Entity
public class Convite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@NotNull
	@ManyToOne
	private Bolao bolao;
	@NotBlank
	private @Email String email;
	@NotNull
	@Future
	private LocalDate dataExpiracao;
	@PastOrPresent
	private LocalDate dataUso;

	@Deprecated
	public Convite() {
	}
	
	public Convite(@NotNull Bolao bolao, @Min(1)int diasParaExpirar,@NotBlank @Email String email) {
		this.bolao = bolao;
		this.dataExpiracao = LocalDate.now().plusDays(diasParaExpirar);
		this.email = email;
	}

	@Override
	public String toString() {
		return "Convite [bolao=" + bolao + ", email=" + email + ", dataExpiracao=" + dataExpiracao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolao == null) ? 0 : bolao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Convite other = (Convite) obj;
		if (bolao == null) {
			if (other.bolao != null)
				return false;
		} else if (!bolao.equals(other.bolao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public boolean taValido() {
		return LocalDate.now().compareTo(this.dataExpiracao) <= 0 && this.dataUso == null ;
	}

	public void usado() {
		Assert.state(dataUso == null, "Voce nao deveria chamar o metodo, pois o convite ja foi usado!!");
		this.dataUso = LocalDate.now();
	}
	
	

}
