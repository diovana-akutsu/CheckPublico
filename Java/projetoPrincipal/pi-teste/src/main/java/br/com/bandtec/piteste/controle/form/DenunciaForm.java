package br.com.bandtec.piteste.controle.form;

import br.com.bandtec.piteste.enums.DenunciaType;

public class DenunciaForm {

    private DenunciaType denuncia;

    private String comentario;

    public DenunciaType getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaType denuncia) {
        this.denuncia = denuncia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "DenunciaForm{" +
                "denuncia=" + denuncia +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
