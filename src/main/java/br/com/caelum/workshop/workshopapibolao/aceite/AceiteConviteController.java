package br.com.caelum.workshop.workshopapibolao.aceite;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.workshop.workshopapibolao.novobolao.Convite;

@RestController
public class AceiteConviteController {
	
	@Autowired
	private ConviteRepository conviteRepository;
	
	@PostMapping(value = "/api/convite/{id}/aceite")
	@Transactional
	public ResponseEntity<?> postMethodName(@PathVariable("id") Long id) {
		Convite convite = conviteRepository.findById(id).get();
		
		if(!convite.taValido()) {
			throw new NotFoundException();
		}
		
		convite.usado();
		return ResponseEntity.notFound().build();
	}

}
