package br.com.bandtec.testeObserver.dominio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_publicacao;

    private String descricao;
    private LocalDate inicio_obra;
    private LocalDate final_obra;
    private Double orcamento;
    private String imagem_obra;

    @ManyToOne
    //name = "fk_usuario", referencedColumnName = "id_usuario",
    @JoinColumn(name = "fk_usuario",nullable = false)
    private Usuario usuario;

    @ManyToOne
    //name = "fk_obra", referencedColumnName = "id_usuario",
    @JoinColumn(name = "fk_obra",nullable = false)

    private Obra obra;

    public Integer getId_publicacao() {
        return id_publicacao;
    }

    public void setId_publicacao(Integer id_publicacao) {
        this.id_publicacao = id_publicacao;
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
}
