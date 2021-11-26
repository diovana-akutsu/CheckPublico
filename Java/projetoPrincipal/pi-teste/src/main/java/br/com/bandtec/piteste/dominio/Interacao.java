package br.com.bandtec.piteste.dominio;

import br.com.bandtec.piteste.enums.DenunciaType;

import javax.persistence.*;

@Entity
public class Interacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_interacao;

   @ManyToOne
   @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
   @ManyToOne
   @JoinColumn(name = "fk_publicacao")
   private Publicacao publicacao;

    private String tipo;
    private String comentario;
    private Boolean curtir;

    @OneToOne
    @JoinColumn(name = "fk_interacao")
    private Interacao interacao;

    @Column(name = "tipo_denuncia")
    private DenunciaType denuncia;

    public Interacao(Integer id_interacao, Usuario usuario, Publicacao publicacao, String tipo, String comentario, Boolean curtir) {
        this.id_interacao = id_interacao;
        this.usuario = usuario;
        this.publicacao = publicacao;
        this.tipo = tipo;
        this.comentario = comentario;
        this.curtir = curtir;
    }

    public Interacao(Integer id_interacao, Usuario usuario, Publicacao publicacao, String tipo, String comentario, Boolean curtir, Interacao interacao) {
        this.id_interacao = id_interacao;
        this.usuario = usuario;
        this.publicacao = publicacao;
        this.tipo = tipo;
        this.comentario = comentario;
        this.curtir = curtir;
        this.interacao=interacao;
    }

    public Interacao() {
    }

    // Contrutor para salvar os dados de importação no banco
    public Interacao(Integer id_interacao, String fk_usuario, String fk_publicacao, String tipo, String texto, boolean like) {
    }

    public Integer getId_interacao() {
        return id_interacao;
    }

    public void setId_interacao(Integer id_interacao) {
        this.id_interacao = id_interacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getCurtir() {
        return curtir;
    }

    public void setCurtir(Boolean curtir) {
        this.curtir = curtir;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public Interacao getInteracao() {
        return interacao;
    }

    public void setInteracao(Interacao interacao) {
        this.interacao = interacao;
    }

    public DenunciaType getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaType denuncia) {
        this.denuncia = denuncia;
    }
}
