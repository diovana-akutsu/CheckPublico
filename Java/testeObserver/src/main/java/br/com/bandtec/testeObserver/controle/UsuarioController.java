package br.com.bandtec.testeObserver.controle;

import br.com.bandtec.testeObserver.dominio.Usuario;
import br.com.bandtec.testeObserver.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioRepository usuarioRepository;
    private List<Usuario> usuario = new ArrayList<>();

    @GetMapping
    public ResponseEntity getUsuario(){
        usuario = usuarioRepository.findAll();

        if (usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).body(usuario);
        }
    }

    @PostMapping
    public ResponseEntity postUsuario(@RequestBody Usuario adicionarUsuario){
        usuarioRepository.save(adicionarUsuario);
        return ResponseEntity.status(201).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }  else {
            return ResponseEntity.notFound().build();
        }
    }
}
