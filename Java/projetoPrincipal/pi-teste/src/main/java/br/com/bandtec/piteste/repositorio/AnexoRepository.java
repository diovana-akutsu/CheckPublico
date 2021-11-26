package br.com.bandtec.piteste.repositorio;

import br.com.bandtec.piteste.dominio.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnexoRepository extends JpaRepository<Anexo, Integer> {

    Optional<Anexo> findByPublicacaoId(Integer id);

}
