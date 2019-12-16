package br.com.caelum.workshop.workshopapibolao.novobolao;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Emails {

	@Async
	public void enviaConvites(TemConvites temConvites) {
		temConvites.getConvites().forEach(convite -> {
			System.out.println("Enviando convite: " + convite);
		});
	}

	
}
