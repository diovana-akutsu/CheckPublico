package br.com.bandtec.piteste.repositorio;

import br.com.bandtec.piteste.dominio.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PublicacaoRepository extends JpaRepository<Publicacao,Integer> {

    Optional<Publicacao> findById(Integer id);

    List<Publicacao> findByObra_NomeContaining(String nomeObra);

    Optional<Publicacao> findByUuidAndUsuario_Id(String uuid, Integer id);

    List<Publicacao> findAllByObra_CepAndObra_Numero(String cep, String numero);

    List<Publicacao> findAllByStatus(String status);

}
