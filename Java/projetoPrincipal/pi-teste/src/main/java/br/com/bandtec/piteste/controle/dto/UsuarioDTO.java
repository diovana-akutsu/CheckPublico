package br.com.bandtec.piteste.controle.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioDTO {
    @NotNull
    @NotEmpty
    @Size(min =10, max =150)
    private String nome;
    @NotNull
    @NotEmpty
    @Email
    @Size(min =5, max=150)
    private String email;
    @NotNull
    @NotEmpty
    private String senha;

    public UsuarioDTO(@NotNull @NotEmpty @Size(min = 10, max = 150) String nome, @NotNull @NotEmpty @Size(min = 5, max = 150) String email, @NotNull @NotEmpty String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
