package br.com.bandtec.piteste.dominio;

import javax.persistence.*;

@Entity
public class Notificados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_notificado;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "fk_obra")
    private Obra obra;

    public Notificados(Usuario usuario, Obra obra) {
        this.usuario = usuario;
        this.obra = obra;
    }

    public Notificados() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId_notificado() {
        return id_notificado;
    }

    public void setId_notificado(Integer id_notificado) {
        this.id_notificado = id_notificado;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
}
