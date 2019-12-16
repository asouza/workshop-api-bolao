package br.com.caelum.workshop.workshopapibolao.novocampeonato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.hql.spi.id.inline.IdsClauseBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.caelum.workshop.workshopapibolao.newteam.Time;
import br.com.caelum.workshop.workshopapibolao.newteam.TimeRepository;

public class NovoCampeonatoForm {

	@NotBlank
	private String nome;

	@NotNull
	@FutureOrPresent
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataComeco;

	@Min(1)
	private int numeroTime;
	@Size(min = 1)
	private List<Long> idTimes = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public List<Long> getIdTimes() {
		return idTimes;
	}

	public void setIdTimes(List<Long> idTimes) {
		this.idTimes = idTimes;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataComeco() {
		return dataComeco;
	}

	public void setDataComeco(LocalDate dataComeco) {
		this.dataComeco = dataComeco;
	}

	public int getNumeroTime() {
		return numeroTime;
	}

	public void setNumeroTime(int numeroTime) {
		this.numeroTime = numeroTime;
	}

	public Campeonato toModel(TimeRepository timeRepository ) {
		Collection<Time> times = timeRepository.findAllById(idTimes);
		return new Campeonato(nome, dataComeco, numeroTime,times);
	}

	public boolean numeroTimesIgualATimesSelecionados() {
		return this.numeroTime == idTimes.size();
	}
	

}
