package br.com.bandtec.projetopi.controller;


import br.com.bandtec.projetopi.Gravar.GravarArquivo;
import br.com.bandtec.projetopi.dominio.Publicacao;
import br.com.bandtec.projetopi.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository publicacaoRepository;
    private Object Publicacao;

    @GetMapping
    public ResponseEntity getPublicacao(){
        List<Publicacao> publicacoes = publicacaoRepository.findAll();
        if (publicacoes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPublicacao(@PathVariable int id){
        if (id <=0){
            return ResponseEntity.badRequest().body("o id deve ser maior que 0");
        }
        return ResponseEntity.of(publicacaoRepository.findById(id));
    }


    @PostMapping("/publicar")
    public ResponseEntity publi(@RequestBody Publicacao publicarNovo) {
        publicacaoRepository.save(publicarNovo);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/download/txt")
    public HttpEntity<byte[]> downloadTXT() throws IOException{
        GravarArquivo gravarArquivo = new GravarArquivo(this.publicacaoRepository);
        gravarArquivo.gerarArquivoHeaderCorpoTrailler(true);
        HttpHeaders httpHeaders = new HttpHeaders();
        FileWriter arq = new FileWriter("C:/Projeto-Check/pi-3adsb-2021-1-grupo-2/Obras.txt");
        PrintWriter gravar = new PrintWriter(arq);
        gravar.print(this.publicacaoRepository.getGravarArq("hospital São Francisco", "02820020"));
        arq.close();
        byte[] arquivo = Files.readAllBytes(Paths.get("C:/Projeto-Check/pi-3adsb-2021-1-grupo-2/Obras.txt"));
        httpHeaders.add("Content-Disposition", "attachment;filename=\"Obras.txt\"");
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
        return entity;
    }

    @GetMapping("/download/csv")
    public HttpEntity<byte[]> downloadCSV() throws  IOException{
        GravarArquivo gravarArquivo = new GravarArquivo(this.publicacaoRepository);
        gravarArquivo.gerarArquivoHeaderCorpoTrailler(true);
        HttpHeaders httpHeaders = new HttpHeaders();
        FileWriter arq = new FileWriter("C:/Projeto-Check/pi-3adsb-2021-1-grupo-2/Obras.csv");
        PrintWriter gravar = new PrintWriter(arq);
        gravar.print(this.publicacaoRepository.getGravarArq("hospital São Francisco", "02820020"));
        arq.close();
        byte[] arquivo = Files.readAllBytes(Paths.get("C:/Projeto-Check/pi-3adsb-2021-1-grupo-2/Obras.csv"));
        httpHeaders.add("Content-Disposition", "attachment;filename=\"Obras.csv\"");
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
        return entity;
    }

}
