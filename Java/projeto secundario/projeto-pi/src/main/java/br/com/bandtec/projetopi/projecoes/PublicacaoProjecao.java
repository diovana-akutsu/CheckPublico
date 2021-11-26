package br.com.bandtec.projetopi.projecoes;

import java.time.LocalDate;

public interface PublicacaoProjecao {
//
//     this.idPublicacao = idPublicacao;
//        this.nome = nome;
//        this.descricao = descricao;
//        this.inicioObra = inicioObra;
//        this.finalObra = finalObra;
//        this.orcamento = orcamento;
//        this.imagemObra = imagemObra;
//        this.CEP = CEP;
//        this.logradoura = logradoura;
//        this.numero = numero;
//        this.cidade = cidade;
//        this.estado = estado;

    Integer getPubliId();
    String getPubliNome();
    String getPubliDescricao();
    LocalDate getPubliInicioObra();
    LocalDate getPubliFinalObra();
    Integer getPubliOrcamento();
    String getPubliImagemObra();
    String getPubliCEP();
    String getPubliLogradoura();
    Integer getPubliNumero();
    String getPubliCidade();
    String gePublitEstado();

}
