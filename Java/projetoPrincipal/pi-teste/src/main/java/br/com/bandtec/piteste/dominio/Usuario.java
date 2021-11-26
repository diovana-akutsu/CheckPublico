package br.com.bandtec.piteste.dominio;

import br.com.bandtec.piteste.controle.UsuarioController;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario extends Usavel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min =10, max =150)
    private String nome;
    @NotNull
    @NotEmpty
    @Size(min =5, max=150)
    private String email;
    @NotNull
    @NotEmpty
    private String senha;

   @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfils= new ArrayList<Perfil>();

    public Usuario(Integer id_usuario,String nome, String email, String senha) {
        this.id = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public Usuario() {

    }

    public Integer getId_usuario() {
        return id;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id = id_usuario;
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

    @Override
    public void comentar() {

    }

    @Override
    public void like() {
        UsuarioController asd = new UsuarioController();
        asd.getUsuario();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfils;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
