package br.com.caelum.workshop.workshopapibolao.novocampeonato;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NumeroDeTimesEQuantidadeSelecionadaTemQueSerIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoCampeonatoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoCampeonatoForm form = (NovoCampeonatoForm) target;

		if (!form.numeroTimesIgualATimesSelecionados()) {
			errors.rejectValue("idTimes", null, "ids dos times precisa ser passado");
		}
	}

}
