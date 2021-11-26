package br.com.bandtec.projetopi.repository;

import br.com.bandtec.projetopi.dominio.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    @Query(value = "select  from publicacao p where p.id = ?;", nativeQuery = true)
    Publicacao getidPublicacao(int id);

    @Query(value = "select * from publicacao where nome = ? and CEP = ?;", nativeQuery = true)
    Publicacao getGravarArq(String nome, String CEP);

//    @Query(value =  "insert into Publicacoes(nome, descricao, inicioObra, finalObra, orcamento, CEP, logradouro, numero, cidade, estado) values(?,?,?,?,?,?,?,?,?,?);", nativeQuery = true)
//    Publicacao publicar(String nome, String descricao, Date inioObra, Date finalObra, Integer orcamento, String CEP, String logradouro, Integer numero, String cidade, String estado);

    //    @Query(value = "select * from user_cadastrado where email = ?", nativeQuery = true)
////    Cadastrado findByEmail(String email);
}
