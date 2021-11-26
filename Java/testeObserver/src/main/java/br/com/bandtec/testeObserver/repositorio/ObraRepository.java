package br.com.bandtec.testeObserver.repositorio;

import br.com.bandtec.testeObserver.dominio.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
}
