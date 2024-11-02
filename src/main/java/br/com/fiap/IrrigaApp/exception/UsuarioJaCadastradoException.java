package br.com.fiap.IrrigaApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsuarioJaCadastradoException extends RuntimeException {

    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
}
