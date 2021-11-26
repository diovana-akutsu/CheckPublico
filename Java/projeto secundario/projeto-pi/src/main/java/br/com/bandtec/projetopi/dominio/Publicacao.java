package br.com.bandtec.projetopi.dominio;

import br.com.bandtec.projetopi.repository.PublicacaoRepository;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublicacao;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;
    private LocalDateTime inicioObra;
    private LocalDateTime finalObra;
    private Integer orcamento;
    private String imagemObra;

    @Column(nullable = false, length = 20)
    private String CEP;
    private String logradoura;
    private int numero;

    @Column(nullable = false, length = 45)
    private String cidade;

    @Column(nullable = false, length = 45)
    private String estado;


    public Publicacao(int idPublicacao, String nome, String descricao, LocalDateTime inicioObra, LocalDateTime finalObra, Integer orcamento, String imagemObra, String CEP, String logradoura, int numero, String cidade, String estado) {
        this.idPublicacao = idPublicacao;
        this.nome = nome;
        this.descricao = descricao;
        this.inicioObra = inicioObra;
        this.finalObra = finalObra;
        this.orcamento = orcamento;
        this.imagemObra = imagemObra;
        this.CEP = CEP;
        this.logradoura = logradoura;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Publicacao(PublicacaoRepository publicacaoRepository) {
    }

    public Publicacao() {

    }


    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public int getIdPublicacao() {
        return idPublicacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicioObra() {
        return inicioObra;
    }

    public void setInicioObra(LocalDateTime inicioObra) {
        this.inicioObra = inicioObra;
    }

    public LocalDateTime getFinalObra() {
        return finalObra;
    }

    public void setFinalObra(LocalDateTime finalObra) {
        this.finalObra = finalObra;
    }

    public Integer getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Integer orcamento) {
        this.orcamento = orcamento;
    }

    public String getImagemObra() {
        return imagemObra;
    }

    public void setImagemObra(String imagemObra) {
        this.imagemObra = imagemObra;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getLogradoura() {
        return logradoura;
    }

    public void setLogradoura(String logradoura) {
        this.logradoura = logradoura;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
