package br.com.bandtec.piteste.controle;

import br.com.bandtec.piteste.controle.dto.UsuarioDTO;
import br.com.bandtec.piteste.dominio.Usuario;
import br.com.bandtec.piteste.model.Info;
import br.com.bandtec.piteste.model.ListaObj;
import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioRepository userRepository;
    private br.com.bandtec.piteste.model.Usuario usuarioModel;

    private AuthenticationManagerBuilder auth;

    private List<Usuario> usuario = new ArrayList<>();

    @GetMapping("/login/{email}")
    public ResponseEntity logar(@RequestHeader(required = true) String email) {
        this.usuarioModel = new br.com.bandtec.piteste.model.Usuario(userRepository);
        if (email.equals("")) {
            return ResponseEntity.status(404).body("Campos inválidos!");
        }
        Info info = usuarioModel.logarUser(email);
        return ResponseEntity.status(info.getAtivo()).body(info.getInfo());
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity getUsuario() {
        usuario = userRepository.findAll();

        if (usuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).body(usuario);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity postUsuario(@RequestBody @Valid UsuarioDTO adicionarUsuario) {
        System.out.println("AQUI: " + adicionarUsuario.getEmail());
        Optional<Usuario> usuario = userRepository.findByEmail(adicionarUsuario.getEmail());
        if (usuario.isPresent()) {
            return ResponseEntity.status(400).build();
        } else {
            String novaSenha = new BCryptPasswordEncoder().encode(adicionarUsuario.getSenha());
            adicionarUsuario.setSenha(novaSenha);
            userRepository.save(new Usuario(adicionarUsuario.getNome(), adicionarUsuario.getEmail(), adicionarUsuario.getSenha()));
            return ResponseEntity.status(201).build();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity putUsuario(@PathVariable int id, @RequestBody Usuario usuarioAlterado) {
        if (userRepository.existsById(id)) {
            usuarioAlterado.setId_usuario(id);
            String novaSenha = new BCryptPasswordEncoder().encode(usuarioAlterado.getSenha());
            usuarioAlterado.setSenha(novaSenha);
            userRepository.save(usuarioAlterado);
            return ok().build();
        } else
            return notFound().build();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
