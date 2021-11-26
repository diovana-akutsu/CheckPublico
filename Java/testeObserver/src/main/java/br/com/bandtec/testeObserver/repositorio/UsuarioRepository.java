package br.com.bandtec.testeObserver.repositorio;

import br.com.bandtec.testeObserver.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
