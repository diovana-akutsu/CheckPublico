package br.com.bandtec.piteste.controle;

import br.com.bandtec.piteste.controle.dto.PublicacaoDto;
import br.com.bandtec.piteste.dominio.Anexo;
import br.com.bandtec.piteste.dominio.Publicacao;
import br.com.bandtec.piteste.dominio.Usuario;
import br.com.bandtec.piteste.model.LeArquivo;
import br.com.bandtec.piteste.repositorio.AnexoRepository;
import br.com.bandtec.piteste.repositorio.InteracaoRepository;
import br.com.bandtec.piteste.repositorio.PublicacaoRepository;
import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    @Autowired
    private PublicacaoRepository repositoryPublicacao;

    @Autowired
    private AnexoRepository repository;

    @Autowired
    private InteracaoRepository interacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    LeArquivo lerArquivo = new LeArquivo();


    @PostMapping("/imagem")
    public ResponseEntity enviarImagem(@RequestParam MultipartFile arquivo) throws IOException {

        Publicacao publicacaoPilha = (Publicacao) PublicacaoController.ultimasPublicacoes.pop();

        Anexo novoAnexo = new Anexo();
        novoAnexo.setNomeArquivo(arquivo.getOriginalFilename());
        novoAnexo.setConteudoArquivo(arquivo.getBytes());
        novoAnexo.setTipoArquivo(arquivo.getContentType());

        Optional<Publicacao> publicacao = repositoryPublicacao.findByUuidAndUsuario_Id(publicacaoPilha.getUuid(), publicacaoPilha.getUsuario().getId_usuario());

        System.out.println("TESTE:"+publicacaoPilha.getData_criacao()+ publicacaoPilha.getUsuario().getId_usuario());
        if (publicacao.isPresent()) {
            if (novoAnexo.getNomeArquivo().endsWith(".jpeg") ||
                    novoAnexo.getNomeArquivo().endsWith(".png") ||
                    novoAnexo.getNomeArquivo().endsWith(".jpg")) {

                novoAnexo.setPublicacao(publicacao.get());
                repository.save(novoAnexo);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(400).body("Você deve fazer o upload de um arquivo de imagem !");
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{idPublicacao}")
    @ResponseBody
    public ResponseEntity download(@PathVariable int idPublicacao) {
        Optional<Anexo> anexoOptional = repository.findByPublicacaoId(idPublicacao);
        if (anexoOptional.isPresent()) {
            Anexo anexo = anexoOptional.get();

            return ResponseEntity.status(200)
                    .header("content-type", anexo.getTipoArquivo())
                    .header("content-disposition", "filename=" + anexo.getNomeArquivo())
                    .body(anexo.getConteudoArquivo());
            /*
            Nos inserirmos 2 headers (cabeçalhos) de resposta usando campos do Anexo
            Content-Type -> Para indicar o tipo do arquivo para download (ou exibição, em caso de imagens, por exemplo)
            Content-Disposition -> Para indicar o nome do arquivo que será sugerido ao navegador
             */
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    @PostMapping("/importacao")
    public ResponseEntity enviarAnexo(@RequestParam MultipartFile arquivo) throws IOException {

        Anexo novoAnexo = new Anexo();
        //imagem_obra
        novoAnexo.setNomeArquivo(arquivo.getOriginalFilename());
        novoAnexo.setConteudoArquivo(arquivo.getBytes());
        novoAnexo.setTipoArquivo(arquivo.getContentType());


        if (novoAnexo.getNomeArquivo().endsWith(".txt")) {
            // implementar codigo de importação
            lerArquivo.leArquivo(novoAnexo.getNomeArquivo(), usuarioRepository, interacaoRepository);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).body("Você deve fazer o upload de um arquivo txt !");
        }

    }

}
