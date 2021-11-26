package br.com.bandtec.projetopi.repository;

import br.com.bandtec.projetopi.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
//    @Query(value = "select * from user_cadastrado where email = ? and senha = ?", nativeQuery = true)
//    Cadastrado logar(String email, String senha);
//
//    @Query(value = "select * from user_cadastrado where email = ?", nativeQuery = true)
////    Cadastrado findByEmail(String email);
//
//    @Modifying
//    @Transactional
//    @Query(value = "insert into user_cadastrado(nome, email, senha) values(?, ?, ?)", nativeQuery = true)
//    Integer savePessoa(String nome, String email, String senha);

}
