package br.com.bandtec.testeObserver.repositorio;

import br.com.bandtec.testeObserver.dominio.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
}
