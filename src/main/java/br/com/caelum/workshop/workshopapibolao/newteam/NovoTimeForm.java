package br.com.caelum.workshop.workshopapibolao.newteam;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovoTimeForm {

	@NotBlank
	private String nome;
	@NotNull
	@Past
	@JsonFormat(shape = Shape.STRING,pattern = "dd/MM/yyyy")
	private LocalDate dataFundacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Time toModel() {
		return new Time(nome,dataFundacao);
	}

}
