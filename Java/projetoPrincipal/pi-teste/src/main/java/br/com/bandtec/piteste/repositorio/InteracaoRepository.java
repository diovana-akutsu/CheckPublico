package br.com.bandtec.piteste.repositorio;

import br.com.bandtec.piteste.dominio.Interacao;
import br.com.bandtec.piteste.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InteracaoRepository extends JpaRepository<Interacao, Integer> {
    Optional<Interacao> findById(Integer id);
    List<Interacao> findByPublicacao_Id(Integer id);
    List<Interacao> findByTipoAndPublicacao_Id(String tipo, Integer id);
    List<Interacao> findByOrderByUsuario_IdAsc();

    Optional<Interacao> findByPublicacao_IdAndUsuario_Id(Integer idPublicacao, Integer idUsuario);

    List<Interacao> findByTipoAndPublicacao_IdAndUsuario_Id(String tipo, Integer id,Integer idUsuario);


}
