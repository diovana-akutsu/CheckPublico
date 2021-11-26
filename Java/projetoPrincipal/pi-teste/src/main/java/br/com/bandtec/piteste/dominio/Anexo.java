package br.com.bandtec.piteste.dominio;

import javax.persistence.*;

@Entity
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "tipo_arquivo")
    private String tipoArquivo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_publicacao")
    private Publicacao publicacao;

    @Column(length = 5_000_000, name = "conteudo_arquivo")
    private byte[] conteudoArquivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo;
    }

    public void setConteudoArquivo(byte[] conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }
}
