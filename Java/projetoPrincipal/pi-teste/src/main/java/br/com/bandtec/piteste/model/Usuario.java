package br.com.bandtec.piteste.model;

import br.com.bandtec.piteste.repositorio.UsuarioRepository;

public class Usuario {

    private UsuarioRepository userReposository;
    private Info info = new Info();

    public Usuario(UsuarioRepository userReposository){
        this.userReposository = userReposository;
    }

    public Info logarUser(String email){
        br.com.bandtec.piteste.dominio.Usuario usuario = this.userReposository.logar(email);
        if(usuario == null){
            info.setInfo("Usuário não encontrado");
            info.setAtivo(400);
            return info;
        }
        String token = JWTconfig.generateToken(usuario.getId_usuario());
        info.setInfo(token);
        info.setAtivo(201);
        return info;
    }

}
