package br.com.bandtec.projetopi.controller;

import br.com.bandtec.projetopi.dominio.Usuario;
import br.com.bandtec.projetopi.dominio.Usavel;
import br.com.bandtec.projetopi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private Usuario cadastradoModel;


    //atributo
    private List<Usavel> users = new ArrayList<>();
    private Usuario cadastro;


    @GetMapping
    public ResponseEntity getUsuario(){
        List<Usuario> cadastrados = userRepository.findAll();

        if (cadastrados.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).build();
        }
    }
    //consulta


    //cadastro
    @PostMapping("/adicionarUsuario")
    public String adicionarUsuario(@RequestBody Usuario adicionarUsuario){
        userRepository.save(adicionarUsuario);
        return "Usuário Cadastrado com sucesso" + ResponseEntity.created(null).build();

    }

    //exclusão
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }  else {
            return ResponseEntity.notFound().build();
        }
    }
}
