package br.com.bandtec.piteste.repositorio;

import br.com.bandtec.piteste.dominio.Notificados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface NotificadosRepository extends JpaRepository<Notificados, Integer> {

    Optional<Notificados> findByUsuario_IdAndObra_Id(Integer idUsuario, Integer idObra);

    List<Notificados> findAllByObra_Id(Integer idObra);

    List<Notificados> findAllByUsuario_Id(Integer idUsuario);

}
