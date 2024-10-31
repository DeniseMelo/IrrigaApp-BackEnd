package br.com.fiap.IrrigaApp.service;


import br.com.fiap.IrrigaApp.model.Usuario;
import br.com.fiap.IrrigaApp.repository.UsuarioRepository;
import exception.UsuarioNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado!"));
    }

    @Override
    public Page<Usuario> listarTodosOsUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao);
    }

    @Override
    public void excluir(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado!"));
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario atualizar(Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioAtualizado.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado!"));
        BeanUtils.copyProperties(usuarioAtualizado, usuarioExistente, "usuarioId", "senha");
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public Usuario buscarPeloNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Nome do Usuario nao encontrado!"));
    }

    @Override
    public Usuario buscarPeloEmail(String email) {
        UserDetails userDetails = usuarioRepository.findByEmail(email);
        if (userDetails instanceof Usuario) {
            return (Usuario) userDetails;
        } else {
            throw new UsuarioNaoEncontradoException("Email do Usuario nao encontrado!");
        }
    }
}