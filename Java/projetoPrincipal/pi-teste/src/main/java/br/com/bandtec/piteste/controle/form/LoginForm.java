package br.com.bandtec.piteste.controle.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return  new UsernamePasswordAuthenticationToken(email,senha);
    }
}
