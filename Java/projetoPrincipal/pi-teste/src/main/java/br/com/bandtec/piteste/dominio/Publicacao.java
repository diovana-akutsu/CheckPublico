package br.com.bandtec.piteste.dominio;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_publicacao")
    private Integer id;

    private String descricao;
    private LocalDate inicio_obra;
    private LocalDate final_obra;
    private Double orcamento;
    private String imagem_obra;

    @Column(name = "tipo_arquivo")
    private String tipoArquivo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao= LocalDateTime.now();

    @Column(name = "uuid")
    private String uuid;

    @Column(length = 5_000_000, name = "conteudo_arquivo")
    private byte[] conteudoArquivo;

    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_usuario",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_obra",nullable = false)
    private Obra obra;

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo;
    }

    public void setConteudoArquivo(byte[] conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }

    public Publicacao(Integer id_publicacao, String descricao, LocalDate inicio_obra, LocalDate final_obra, Double orcamento, String imagem_obra, Usuario usuario, Obra obra, String status) {
        this.id = id_publicacao;
        this.descricao = descricao;
        this.inicio_obra = inicio_obra;
        this.final_obra = final_obra;
        this.orcamento = orcamento;
        this.imagem_obra = imagem_obra;
        this.usuario = usuario;
        this.obra = obra;
        this.status = status;
    }

    public Publicacao() {
    }

    public Integer getId_publicacao() {
        return id;
    }

    public void setId_publicacao(Integer id_publicacao) {
        this.id = id_publicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getInicio_obra() {
        return inicio_obra;
    }

    public void setInicio_obra(LocalDate inicio_obra) {
        this.inicio_obra = inicio_obra;
    }

    public LocalDate getFinal_obra() {
        return final_obra;
    }

    public void setFinal_obra(LocalDate final_obra) {
        this.final_obra = final_obra;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public String getImagem_obra() {
        return imagem_obra;
    }

    public void setImagem_obra(String imagem_obra) {
        this.imagem_obra = imagem_obra;
    }


    public LocalDateTime getData_criacao() {
        return dataCriacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.dataCriacao = data_criacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
