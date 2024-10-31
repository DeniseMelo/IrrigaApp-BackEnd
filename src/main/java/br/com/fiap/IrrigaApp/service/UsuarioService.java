package br.com.fiap.IrrigaApp.service;


import br.com.fiap.IrrigaApp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Usuario salvarUsuario(Usuario usuario);

    Usuario buscarPorId(String id);

    Page<Usuario> listarTodosOsUsuarios(Pageable paginacao);

    void excluir(String id);

    Usuario atualizar(Usuario usuarioAtualizado);

    Usuario buscarPeloNome(String nome);

    Usuario buscarPeloEmail(String email);
}