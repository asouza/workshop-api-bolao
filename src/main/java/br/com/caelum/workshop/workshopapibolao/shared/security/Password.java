package br.com.caelum.workshop.workshopapibolao.shared.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Represenha uma senha.
 * 
 * @author alberto
 *
 */
public class Password {

	private String encoded;

	private Password(String text,boolean isRaw) {
		super();
		if(isRaw){
			this.encoded = new BCryptPasswordEncoder().encode(text);
		} else {
			this.encoded = text;
		}
	}
	
	public static Password buildWithRawText(String raw){
		return new Password(raw,true);
	}
	
	public static Password buildWithEncodedText(String raw){
		return new Password(raw,false);
	}
	
	public String getEncoded() {
		return encoded;
	}
	
	public boolean matches(String otherRaw){
		return new BCryptPasswordEncoder().matches(otherRaw, encoded);
	}

}
