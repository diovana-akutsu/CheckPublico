package br.com.bandtec.testeObserver.controle;

import br.com.bandtec.testeObserver.dominio.Publicacao;
import br.com.bandtec.testeObserver.dominio.Usuario;
import br.com.bandtec.testeObserver.repositorio.PublicacaoRepository;
import br.com.bandtec.testeObserver.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {
    @Autowired
    public PublicacaoRepository publicacaoRepository;
    private List<Publicacao> publicacao = new ArrayList<>();

    @GetMapping
    public ResponseEntity getPublicacao(){
        publicacao = publicacaoRepository.findAll();

        if (publicacao.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).body(publicacao);
        }
    }

    @PostMapping
    public ResponseEntity postPublicacao(@RequestBody Publicacao adicionarPublicacao){
        publicacaoRepository.save(adicionarPublicacao);
        return ResponseEntity.status(201).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublicacao(@PathVariable int id){
        if(publicacaoRepository.existsById(id)){
            publicacaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }  else {
            return ResponseEntity.notFound().build();
        }
    }
}
