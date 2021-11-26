package br.com.bandtec.piteste.repositorio;

import br.com.bandtec.piteste.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "select * from usuario where email = ? and ativo = 1;", nativeQuery = true)
    Usuario logar(String email);

    Optional<Usuario> findById(Integer id);

    Optional<Usuario> findByEmail(String email);

}
