package br.com.caelum.workshop.workshopapibolao.shared.security;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Responsável por encodar e decodar o token jwt em função de um secret
 * @author alberto
 *
 */
public final class TokenHandler {

	//talvez não seja o melhor deixar aqui, talvez seja hehee. 
    private final String secret = "jkfdhsjkfhiu34kjsbnvdf";

    public UserDetails parseUserFromToken(String token,Function<String, UserDetails> userLoader) {    	
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userLoader.apply(username);
    }

    public String createTokenForUser(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}