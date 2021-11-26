package br.com.bandtec.piteste.controle;

import br.com.bandtec.piteste.config.security.TokenService;
import br.com.bandtec.piteste.controle.form.DenunciaForm;
import br.com.bandtec.piteste.controle.form.InteracaoForm;
import br.com.bandtec.piteste.dominio.Interacao;
import br.com.bandtec.piteste.dominio.Publicacao;
import br.com.bandtec.piteste.dominio.Usuario;
import br.com.bandtec.piteste.enums.DenunciaType;
import br.com.bandtec.piteste.gravarArquivo.ArquivosTxt;
import br.com.bandtec.piteste.gravarArquivo.ArquivosTxt2;
import br.com.bandtec.piteste.model.FilaObj;
import br.com.bandtec.piteste.repositorio.InteracaoRepository;
import br.com.bandtec.piteste.repositorio.PublicacaoRepository;
import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interacao")
public class InteracaoController {

    @Autowired
    public InteracaoRepository interacaoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public PublicacaoRepository publicacaoRepository;

    @Autowired
    private TokenService tokenService;

    private List<Interacao> interacao = new ArrayList<>();
    private List<Usuario> usuario = new ArrayList<>();

    FilaObj<DenunciaType> lista = new FilaObj(10);

    @GetMapping
    public ResponseEntity getInteracoes() {
        interacao = interacaoRepository.findAll();

        if (interacao.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).body(interacao);
        }
    }
//
//    @GetMapping("/comentarios")
//    public ResponseEntity getComentarios() {
//        interacao = interacaoRepository.findByTipo("comentario");
//
//        if (interacao.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.status(200).body(interacao);
//        }
//    }

    @GetMapping("/comentario/{id}")
    public ResponseEntity getComentarios(@PathVariable int id) {
        interacao = interacaoRepository.findByTipoAndPublicacao_Id("comentario", id);

        if (interacao.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).body(interacao);
        }
    }

    @GetMapping("/like/{id}")
    public ResponseEntity getLike(@PathVariable int id) {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        interacao = interacaoRepository.findByTipoAndPublicacao_IdAndUsuario_Id("like", id, usuario.getId_usuario());

        if (interacao.isEmpty()) {
            return ResponseEntity.status(200).body(false);
        } else {
            return ResponseEntity.status(200).body(true);
        }
    }

    @PostMapping("/{idPublicacao}/curtir")
    public ResponseEntity curtir(@PathVariable Integer idPublicacao) {

        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        List<Interacao> integracao = interacaoRepository.findByTipoAndPublicacao_IdAndUsuario_Id("like", idPublicacao, usuario.getId_usuario());
        if (!integracao.isEmpty()) {
            System.out.println("Encontrado, tem que deletar");
            interacaoRepository.deleteById(integracao.get(0).getId_interacao());
            return ResponseEntity.status(200).body("Like removido!");
        }
        else{
            Interacao i = new Interacao(null, usuario, publicacao, "like", null, true, null);
            interacaoRepository.save(i);
            return ResponseEntity.status(201).body("Like adicionado!");
        }


    }

    @PostMapping("/{idPublicacao}/comentar")
    public ResponseEntity comentar(@RequestBody @Valid InteracaoForm form, @PathVariable Integer idPublicacao) {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        Interacao i = new Interacao(null, usuario, publicacao, "comentario", form.getComentario(), false);
        interacaoRepository.save(i);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{idPublicacao}/denunciar")
    public ResponseEntity denunciar(@RequestBody @Valid DenunciaForm form, @PathVariable Integer idPublicacao) {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        Interacao i = new Interacao(null, usuario, publicacao, "denuncia", form.getComentario(), false);
        i.setDenuncia(form.getDenuncia());

        lista.insert(form.getDenuncia());
        interacaoRepository.save(i);
//        System.out.println("Exibindo nome da denuncia: " + form.getDenuncia().name());
        lista.exibe();
        return ResponseEntity.status(201).body(lista);
    }

    @PostMapping("/{idPublicacao}/{idComentario}/curtir/comentario")
    public ResponseEntity curtirComentario(@PathVariable Integer idUsuario, @PathVariable Integer idPublicacao, @PathVariable Integer idComentario) {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        Interacao interacao = interacaoRepository.findById(idComentario).get();
        Interacao i = new Interacao(null, usuario, publicacao, "like", null, true, interacao);
        interacaoRepository.save(i);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{idComentario}/comentar/comentario")
    public ResponseEntity comentarComentario(@RequestBody @Valid InteracaoForm form, @PathVariable Integer idUsuario, @PathVariable Integer idPublicacao, @PathVariable Integer idComentario) {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        Interacao interacao = interacaoRepository.findById(idComentario).get();
        Interacao i = new Interacao(null, usuario, publicacao, "comentario", form.getComentario(), false, interacao);
        interacaoRepository.save(i);
        return ResponseEntity.status(201).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteInteracao(@PathVariable int id) {
        if (interacaoRepository.existsById(id)) {
            interacaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/txt", produces="application/txt")
    public ResponseEntity downloadTxt() throws IOException {
        interacao = interacaoRepository.findByOrderByUsuario_IdAsc();
        usuario = usuarioRepository.findAll();
        ArquivosTxt2.gerarArquivoHeaderCorpoTrailer(usuario,interacao);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=Obra.txt");
        return ResponseEntity.ok().build();
    }
}
