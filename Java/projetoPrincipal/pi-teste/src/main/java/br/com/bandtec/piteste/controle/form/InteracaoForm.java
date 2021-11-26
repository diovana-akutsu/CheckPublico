package br.com.bandtec.piteste.controle.form;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InteracaoForm {
    @NotNull
    @NotBlank
    @Size(max=200)
    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
