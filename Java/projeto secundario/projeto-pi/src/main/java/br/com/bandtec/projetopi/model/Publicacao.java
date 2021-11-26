package br.com.bandtec.projetopi.model;

import br.com.bandtec.projetopi.repository.PublicacaoRepository;

import java.util.ArrayList;

public class Publicacao {

    private PublicacaoRepository publicacaoRepository;

    public Publicacao(PublicacaoRepository publicacaoRepository) {
        this.publicacaoRepository = publicacaoRepository;
    }

    public ArrayList<br.com.bandtec.projetopi.dominio.Publicacao> publicacaoAll(){
        return (ArrayList<br.com.bandtec.projetopi.dominio.Publicacao>) this.publicacaoRepository.findAll();
    }
}
