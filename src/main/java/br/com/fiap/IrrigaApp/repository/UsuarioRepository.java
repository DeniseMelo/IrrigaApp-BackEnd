package br.com.fiap.IrrigaApp.repository;


import br.com.fiap.IrrigaApp.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    UserDetails findByEmail(String email);

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findById(String id);

    List<Usuario> findAll();
}
