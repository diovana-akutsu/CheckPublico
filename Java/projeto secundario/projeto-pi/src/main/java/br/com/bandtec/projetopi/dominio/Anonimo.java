package br.com.bandtec.projetopi.dominio;

public class Anonimo implements Usavel {


    //metodos

    @Override
    public String getComentar() {
        return "Você não tem permissão para comentar, realize " +
               "seu cadastro para ter acesso a esse recurso !";
    }

    @Override
    public String getVerMapa() {
        return "Você não tem acesso as métricas no mapa, realize " +
               "seu cadastro para ter acesso a esse recurso !";
    }

    @Override
    public String getReagir() {
        return "Você não pode reagir as publicações, " +
                "realize seu cadastro para ter acesso a esse recurso ! ";
    }



}
