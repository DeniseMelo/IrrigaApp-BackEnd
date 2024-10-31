package br.com.fiap.IrrigaApp.security.config;


import br.com.fiap.IrrigaApp.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${MINHA_CHAVE_SECRETA}")
    private String palavraSecreta;

    //gera o token no momento do login
    public String gerarToken (Usuario usuario){

        try{
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            String token = JWT
                    .create()
                    .withIssuer("contatos")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException erro){
            throw new RuntimeException("Não foi possivel gerar o token");
        }
    }

    //validar o token
    public String validarToken(String token){
        try{
            Algorithm algorithm =  Algorithm.HMAC256(palavraSecreta);
            return JWT.require(algorithm)
                    .withIssuer("contatos")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException erro){
            return "";
        }
    }

    //tempo do token de 2 horas
    public Instant gerarDataDeExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

