package br.com.bandtec.piteste.controle;

import br.com.bandtec.piteste.config.security.AutentificacaoViaTokenFilter;
import br.com.bandtec.piteste.config.security.TokenService;
import br.com.bandtec.piteste.controle.dto.PublicacaoDto;
import br.com.bandtec.piteste.dominio.Obra;
import br.com.bandtec.piteste.dominio.Publicacao;
import br.com.bandtec.piteste.dominio.Usuario;
import br.com.bandtec.piteste.model.PilhaObj;
import br.com.bandtec.piteste.repositorio.ObraRepository;
import br.com.bandtec.piteste.repositorio.PublicacaoRepository;
import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    public PublicacaoRepository publiRepository;

    @Autowired
    public ObraRepository obraRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificadoraController notificadora;

    private AutentificacaoViaTokenFilter authent;

    private List<Publicacao> publicacao = new ArrayList<>();

    private List<Publicacao> publicacaoMapa = new ArrayList<>();

    private List<Publicacao> publicacaoAprovadas = new ArrayList<>();

    public static PilhaObj ultimasPublicacoes = new PilhaObj(15);


    @CrossOrigin
    @GetMapping
    public ResponseEntity getPublicacao(@RequestParam(required = false) String nomeObra) {

        if (nomeObra == null) {
            System.out.println("Não tem parametro aqui");
            publicacao = publiRepository.findAll();
            if (publicacao.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(200).body(PublicacaoDto.converter(publicacao));

            }
        } else {
            System.out.println("TEm parametro aqui");
            publicacao = publiRepository.findByObra_NomeContaining(nomeObra);
            if (publicacao.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(200).body(PublicacaoDto.converter(publicacao));
            }
        }

    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getPublicacaoId(@PathVariable int id) {
        Optional<Publicacao> publ = publiRepository.findById(id);

        if (!publ.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(200).body(publ.get());

        }
    }

    @CrossOrigin
    @GetMapping("/{cep}/{numero}")
    public ResponseEntity getPublicacaoMapa(@PathVariable String cep, @PathVariable String numero) {
      publicacaoMapa = publiRepository.findAllByObra_CepAndObra_Numero(cep,numero);

        if (publicacaoMapa.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(200).body(publicacaoMapa);

        }
    }

//     Aprovado
//    Em análise

    @CrossOrigin
    @GetMapping("/aprovadas")
    public ResponseEntity getPublicacoesAprovadas() {
        publicacaoAprovadas = publiRepository.findAllByStatus("Aprovado");

        if (publicacaoAprovadas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(200).body(publicacaoAprovadas);

        }
    }

    @CrossOrigin
    @GetMapping("/em-analise")
    public ResponseEntity getPublicacoesEmAnalise() {
        publicacaoAprovadas = publiRepository.findAllByStatus("Em análise");

        if (publicacaoAprovadas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(200).body(publicacaoAprovadas);

        }
    }

    @CrossOrigin
    @GetMapping("/feed")
    public Page<PublicacaoDto> lista(@PageableDefault(sort = "dataCriacao",
            direction = Sort.Direction.DESC,
            page = 0, size = 10) Pageable paginacao) {

        Page<Publicacao> publi = publiRepository.findAll(paginacao);
        return PublicacaoDto.converterPage(publi);

    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity postPublicacao(@RequestBody @Valid PublicacaoDto novaPublic) {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        System.out.println("CCC:" + usuario);

        int obraEncontrada = obraRepository.countByCepAndNumero(novaPublic.getCep(), novaPublic.getNumero());
        if (obraEncontrada > 0) {
            // Usuario apenas para teste até implementarmos autentificação
            Obra obra = obraRepository.findByCepAndNumero(novaPublic.getCep(), novaPublic.getNumero());
            Publicacao publicacao = new Publicacao(null, novaPublic.getDescricao(), novaPublic.getInicio_obra(),
                    novaPublic.getFinal_obra(), novaPublic.getOrcamento(), novaPublic.getImagem_obra(), usuario, obra, "Em análise");

            UUID uuid = UUID.randomUUID();
            publicacao.setUuid(uuid.toString());
            System.out.println("Não aguento mais: "+uuid.toString());
            publiRepository.save(publicacao);
            ultimasPublicacoes.push(publicacao);
            notificadora.notificarUsuarioObra(publicacao.getObra().getId_obra());

            System.out.println("TESTE:" + publicacao.getData_criacao() + publicacao.getUsuario().getId_usuario());


        } else {
            // Usuario apenas para teste até implementarmos autentificação
            Obra obra = new Obra(null, novaPublic.getNome(), novaPublic.getCategoria(), novaPublic.getCep(),
                    novaPublic.getRua(), novaPublic.getNumero(), novaPublic.getBairro(), novaPublic.getCidade(),
                    novaPublic.getEstado(), null, null);

            Publicacao publicacao = new Publicacao(null, novaPublic.getDescricao(), novaPublic.getInicio_obra(),
                    novaPublic.getFinal_obra(), novaPublic.getOrcamento(), novaPublic.getImagem_obra(), usuario, obra, "Em análise");

            obraRepository.save(obra);
            UUID uuid = UUID.randomUUID();
            publicacao.setUuid(uuid.toString());
            System.out.println("Não aguento mais: "+uuid.toString());
            publiRepository.save(publicacao);
            ultimasPublicacoes.push(publicacao);
            notificadora.notificarUsuarioObra(publicacao.getObra().getId_obra());
            System.out.println("TESTE:" + publicacao.getData_criacao() + publicacao.getUsuario().getId_usuario());


        }
        return ResponseEntity.status(201).build();

    }

    @CrossOrigin
    @PostMapping("cadastro-com-imagem")
    public ResponseEntity postPublicacaoComImagem(@RequestParam MultipartFile arquivo,
                                                  @RequestBody @Valid PublicacaoDto novaPublic) throws IOException {
        System.out.println("TRIGO:" + tokenService.getIdResgatado());
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();
        System.out.println("CCC:" + usuario);
        //imagem_obra == nomeArquivo
        novaPublic.setImagem_obra(arquivo.getOriginalFilename());
        novaPublic.setConteudoArquivo(arquivo.getBytes());
        novaPublic.setTipoArquivo(arquivo.getContentType());

        System.out.println("Publicacao:" + novaPublic.toString());

        if (novaPublic.getImagem_obra().endsWith(".txt")) {
            // implementar codigo de cadastro
            int obraEncontrada = obraRepository.countByCepAndNumero(novaPublic.getCep(), novaPublic.getNumero());
            if (obraEncontrada > 0) {
                // Usuario apenas para teste até implementarmos autentificação
                Obra obra = obraRepository.findByCepAndNumero(novaPublic.getCep(), novaPublic.getNumero());
                Publicacao publicacao = new Publicacao(null, novaPublic.getDescricao(), novaPublic.getInicio_obra(),
                        novaPublic.getFinal_obra(), novaPublic.getOrcamento(), novaPublic.getImagem_obra(), usuario, obra, "Em análise");
                //  publiRepository.save(publicacao);

            } else {
                // Usuario apenas para teste até implementarmos autentificação
                Obra obra = new Obra(null, novaPublic.getNome(), novaPublic.getCategoria(), novaPublic.getCep(),
                        novaPublic.getRua(), novaPublic.getNumero(), novaPublic.getBairro(), novaPublic.getCidade(),
                        novaPublic.getEstado(), null, null);

                Publicacao publicacao = new Publicacao(null, novaPublic.getDescricao(), novaPublic.getInicio_obra(),
                        novaPublic.getFinal_obra(), novaPublic.getOrcamento(), novaPublic.getImagem_obra(), usuario, obra,"Em análise");

                // obraRepository.save(obra);
                // publiRepository.save(publicacao);
            }
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).body("Você deve fazer o upload de um arquivo txt !");
        }


    }

    @PutMapping("/aprovar/{id}")
    public ResponseEntity putUsuario(@PathVariable int id) {
        if (publiRepository.existsById(id)) {
         Publicacao publicAprov = publiRepository.getOne(id);
            publicAprov.setStatus("Aprovado");
            publiRepository.save(publicAprov);
            return ok().build();
        } else
            return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublicacao(@PathVariable int id) {
        if (publiRepository.existsById(id)) {
            publiRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
