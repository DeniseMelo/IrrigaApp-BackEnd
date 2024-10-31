package br.com.fiap.IrrigaApp.controller;

import br.com.fiap.IrrigaApp.model.Usuario;
import br.com.fiap.IrrigaApp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para cadastrar um novo usuário
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario novoUsuario) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    // Atualiza o usuário (nome e senha)
    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizarUsuario(@RequestBody Usuario usuarioAtualizado) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // Define o email autenticado ao objeto de atualização
        usuarioAtualizado.setEmail(email);

        // Atualiza o usuário no serviço
        usuarioService.atualizar(usuarioAtualizado);

        return ResponseEntity.noContent().build();
    }

    // Endpoint para excluir usuário (apenas para ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable String id) {
        usuarioService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint para buscar usuário por ID (apenas para ADMIN)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable String id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // Endpoint para listar todos os usuários (apenas para ADMIN)
    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.listarTodosOsUsuarios(pageable);
        return ResponseEntity.ok(usuarios);
    }
}

