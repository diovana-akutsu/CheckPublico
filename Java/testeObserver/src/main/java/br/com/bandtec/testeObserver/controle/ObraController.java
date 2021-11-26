package br.com.bandtec.testeObserver.controle;

import br.com.bandtec.testeObserver.Service.ListaObj;
import br.com.bandtec.testeObserver.dominio.Obra;
import br.com.bandtec.testeObserver.repositorio.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    @Autowired
    public ObraRepository obraRepository;

    private List<Obra> obra = new ArrayList<>();
    private ListaObj listObj;
    private ListaObj <Obra> lista = new ListaObj<>(3);


    @GetMapping
    public ResponseEntity getObra(){
        obra = obraRepository.findAll();

        if (obra.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(200).body(obra);
        }
    }

    @PostMapping
    public ResponseEntity postObra(@RequestBody Obra adicionarObra){
        obraRepository.save(adicionarObra);
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
}
