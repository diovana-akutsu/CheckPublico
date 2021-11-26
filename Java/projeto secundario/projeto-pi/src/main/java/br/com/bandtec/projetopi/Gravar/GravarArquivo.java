package br.com.bandtec.projetopi.Gravar;


import br.com.bandtec.projetopi.repository.PublicacaoRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GravarArquivo {

    private PublicacaoRepository publicacaoRepository;

    public GravarArquivo(PublicacaoRepository publicacaoRepository) {
        this.publicacaoRepository = publicacaoRepository;
    }

    public static void gravaRegistro (String nomeArq, String registro){
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s. \n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException e){
            System.err.printf("Erro ao gravar arquivo: %s. \n", e.getMessage());
        }
    }

    public void gerarArquivoHeaderCorpoTrailler(boolean txt) {
        br.com.bandtec.projetopi.model.Publicacao publicacao = new br.com.bandtec.projetopi.model.Publicacao(this.publicacaoRepository);
        ArrayList<br.com.bandtec.projetopi.dominio.Publicacao> publicacoes = publicacao.publicacaoAll();
        String nomeArq ="";
        String demlimitador = "";
        if (txt) {
            nomeArq = "Obras.txt";
            demlimitador = " ";
        } else {
            nomeArq = "Obras.csv";
            demlimitador = " ";
        }
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dateDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (txt) {
            header += " 01 ";
            header += " 1.0 VERÇÃO ";
            header += " OBRAS ";
            header += formatter.format(dateDeHoje);

            gravaRegistro(nomeArq, header);
            corpo += String.format("%10s%29s%n", " NOME ", " CEP ");
        }
        for (int i = 0; i < publicacoes.size(); i++){
            corpo += " 02 ";

            corpo += String.format("%-30s", publicacoes.get(i).getNome() + demlimitador);
            corpo += String.format("%-20s", publicacoes.get(i).getCEP());
            contRegDados++;
            gravaRegistro(nomeArq, corpo);
        }
        if (txt) {
            trailer += "03";
            trailer += String.format("%010d CONTAGEM DE DADOS ", contRegDados);
            gravaRegistro(nomeArq, trailer);
        }

    }


}
