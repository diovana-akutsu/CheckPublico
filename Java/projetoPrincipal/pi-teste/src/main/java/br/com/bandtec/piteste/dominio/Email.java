package br.com.bandtec.piteste.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Email {
    private String email="";
    private String texto="";

    public Email(String email, String texto) {
        this.email = email;
        this.texto = texto;
    }

    public Email() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
