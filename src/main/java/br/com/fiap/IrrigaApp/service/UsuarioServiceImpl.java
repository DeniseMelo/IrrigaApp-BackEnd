package br.com.fiap.IrrigaApp.service;


import br.com.fiap.IrrigaApp.exception.UsuarioJaCadastradoException;
import br.com.fiap.IrrigaApp.exception.UsuarioNaoEncontradoException;
import br.com.fiap.IrrigaApp.model.Usuario;
import br.com.fiap.IrrigaApp.repository.UsuarioRepository;
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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario salvarUsuario(Usuario usuario) {
        // Verifica se o email já existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {

            throw new UsuarioJaCadastradoException("Usuário com este e-mail já está cadastrado.");
        }

        // Valida campos obrigatórios
        if (usuario.getNome() == null || usuario.getNome().isEmpty() ||
                usuario.getEmail() == null || usuario.getEmail().isEmpty() ||
                usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }

        // Criptografa a senha e salva o usuário
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
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