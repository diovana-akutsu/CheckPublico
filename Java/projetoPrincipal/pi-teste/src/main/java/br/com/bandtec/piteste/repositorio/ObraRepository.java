package br.com.bandtec.piteste.repositorio;

import br.com.bandtec.piteste.dominio.Obra;
import br.com.bandtec.piteste.dominio.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ObraRepository extends JpaRepository<Obra, Integer> {

    Optional<Obra> findById(Integer id);
    List<Obra> findByCategoriaContaining(String nomeObra);
    Obra findByCepAndNumero(String cep, String numero);
    int countByCepAndNumero(String cep, String numero);

    @Query(value = "select * from Obra where nome = ? and " +
            "categoria = ? and cep = ? ",
            nativeQuery = true)
    Obra findObra();

    @Override
    List<Obra> findAll();


}
