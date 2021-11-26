package br.com.bandtec.piteste.controle;

import br.com.bandtec.piteste.dominio.Obra;
import br.com.bandtec.piteste.gravarArquivo.ArquivosTxt;
import br.com.bandtec.piteste.gravarArquivo.ArquivosCsv;
import br.com.bandtec.piteste.model.ListaObj;
import br.com.bandtec.piteste.repositorio.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    @Autowired
    public ObraRepository obraRepository;

    private List<Obra> obra = new ArrayList<>();
    private ListaObj listaObj;
    private ListaObj <Obra> lista = new ListaObj<>(3);



    @GetMapping
    public ResponseEntity getObra(@RequestParam(required = false) String categoriaObra){
        if(categoriaObra == null){
            System.out.println("Não tem parametro aqui");
            obra = obraRepository.findAll();
            if (obra.isEmpty()){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(200).body(obra);
            }
        } else {
            System.out.println("Tem parâmetro aqui");
            obra = obraRepository.findByCategoriaContaining(categoriaObra);
            if (obra.isEmpty()){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(200).body(obra);
            }
        }

    }

    @PostMapping
    public ResponseEntity postObra(@RequestBody Obra adicionarObra){
        obraRepository.save(adicionarObra);
        listaObj.adiciona(adicionarObra);
        return ResponseEntity.status(201).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteObra(@PathVariable int id){
        if(obraRepository.existsById(id)){
            obraRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }  else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putObra(@PathVariable int id,@RequestBody Obra obraAlterado){
        if(obraRepository.existsById(id)){
            obraAlterado.setId_obra(id);
            obraRepository.save(obraAlterado);
            return ok().build();
        }
        else
            return notFound().build();
    }
    //adicionando valores a lista
    @PostMapping("/implementar")
    public ResponseEntity postList(@RequestBody Obra adiconarObras){
        obraRepository.save(adiconarObras);
        lista.adiciona(adiconarObras);
        return ResponseEntity.created(null).build();
    }

//    //trazendo a lista
//    @GetMapping("/lista")
//    public Iterable<Obra> getObras(){
//        lista.exibe();
//        return obraRepository.findAll();
//    }

    //exportando arquivo csv
    @GetMapping(value = "/csv", produces="application/csv")
    public ResponseEntity downloadCSV() throws IOException{
        obra = obraRepository.findAll();
        ArquivosCsv.gravaObras(obra);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=Obra.csv");
        return ResponseEntity.ok().build();
    }

    //exportando arquivo txt
    @GetMapping(value = "/txt", produces="application/txt")
    public ResponseEntity downloadTxt() throws IOException{
        obra = obraRepository.findAll();
        ArquivosTxt.gerarArquivoHeaderCorpoTrailer(obra);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=Obra.txt");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/uptxt", produces="application/txt")
    public ResponseEntity uploadTxt() throws IOException{
        obra = obraRepository.findAll();
        ArquivosTxt.leArquivo("Obras.txt");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=Obra.txt");
        return ResponseEntity.ok().build();
    }
}
