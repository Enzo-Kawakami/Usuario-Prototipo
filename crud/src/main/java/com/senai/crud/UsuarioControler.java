package com.senai.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioControler {

    @Autowired
    private UsuarioRepository usuarioRepository; // Conecta com o banco de dados

    // 1. CREATE (Salvar usuário no banco)
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // 2. READ (Listar todos os usuários do banco)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // 3. UPDATE (Atualizar os dados de um usuário existente)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario dadosAtualizados) {
        Optional<Usuario> usuarioOp = usuarioRepository.findById(id);

        if (usuarioOp.isPresent()) {
            Usuario usuarioExistente = usuarioOp.get();
            usuarioExistente.setNome(dadosAtualizados.getNome());
            usuarioExistente.setIdade(dadosAtualizados.getIdade());
            usuarioExistente.setDtnasc(dadosAtualizados.getDtnasc());

            Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
            return ResponseEntity.ok(usuarioSalvo); // ADICIONADO 'return' AQUI
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."); // ADICIONADO 'return' AQUI
    }

    // 4. DELETE (Deletar usuário do banco pelo ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }
}
