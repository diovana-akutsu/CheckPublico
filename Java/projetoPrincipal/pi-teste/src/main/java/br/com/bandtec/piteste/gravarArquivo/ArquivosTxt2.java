package br.com.bandtec.piteste.gravarArquivo;

import br.com.bandtec.piteste.dominio.Interacao;
import br.com.bandtec.piteste.dominio.Publicacao;
import br.com.bandtec.piteste.dominio.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArquivosTxt2 {

    //
    public static void gerarArquivoHeaderCorpoTrailer(List<Usuario> listaUsuario, List<Interacao> listaInteração){
        String nomeArq ="Dados.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0,contInteracao=0;

        Date dateDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00";
        header += "Interacoes";
        header += formatter.format(dateDeHoje);
        header += "01";

        //gravando registro no header
        gravarRegistro(nomeArq, header);

        //informações de coluna txt
//            corpo += String.format("%29s%20s%12s", " NOME ", " CATEGORIA ", " CEP ");


        for (int i = 0; i < listaUsuario.size(); i++){
            Usuario a = listaUsuario.get(i);
            corpo += "01";
            corpo += String.format("%03d", a.getId_usuario());
            corpo += String.format("%-30s", a.getNome());
            corpo += String.format("%-50s", a.getEmail());
            corpo += String.format("%-60s", a.getSenha());
            contRegDados++;
            gravarRegistro(nomeArq, corpo);
            corpo = "";
            for (; contInteracao < listaInteração.size(); contInteracao++){
                Interacao in = listaInteração.get(contInteracao);
                Usuario fkUser = in.getUsuario();
                Publicacao fkPublicacao = in.getPublicacao();
                if (fkUser.getId_usuario() == a.getId_usuario()){
                    corpo += "02";
                    corpo += String.format("%03d", in.getId_interacao());
                    corpo += String.format("%-10s", in.getTipo());
                    if (in.getCurtir()){
                        corpo += String.format("%1d", 1);
                    } else{
                        corpo += String.format("%1d", 0);
                    }
                    corpo += String.format("%03d", fkUser.getId_usuario());
                    corpo += String.format("%03d", fkPublicacao.getId_publicacao());
                    corpo += String.format("%-100s", in.getComentario());
                    contRegDados++;
                    gravarRegistro(nomeArq, corpo);
                    corpo = "";
                } else{
                    break;
                }
            }
        }

        trailer += "03";
        trailer += String.format("%05d", contRegDados);
        gravarRegistro(nomeArq, trailer);
    }

    public static void gravarRegistro (String nomeArq, String registro){
        BufferedWriter saida = null;
        try{
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e){
            System.err.printf("Erro na abertura do arquivo: %s. \n", e.getMessage());
        }

        try{
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException e){
            System.err.printf("Erro ao gravar arquivo: %s. \n", e.getMessage());
        }
    }


    /* Fim Gravar Arquivo */
    /*******************************************************************************************************/
    /* Inicio Ler Arquivo */

    public static void leArquivo(String nomeArq) {

         List<Interacao> listaInteracao = new ArrayList<>();
         List<Usuario> listaUsuario = new ArrayList<>();


        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nome, email, senha, tipo,fk_usuario,fk_Publicacao,texto;
        double latitudo, longitude;
        boolean like;
        Integer id_Interacao,id_Publicacao,Id_Usuario;


        int contRegistro=0;

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro
                //    012345
                //    00NOTA
                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 7));
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(7,26));
                    System.out.println("Versão do layout: " + registro.substring(26,28));
                }
                else if (tipoRegistro.equals("01")) {

                    Id_Usuario = Integer.parseInt(registro.substring(2,5));
                    nome = registro.substring(5,35);
                    email = registro.substring(35,55);
                    senha = registro.substring(55,63);

                    Usuario usuario = new Usuario(Id_Usuario,nome,email,senha);
                    listaUsuario.add(usuario);

                    contRegistro++;

                }else if (tipoRegistro.equals("02")) {

                    id_Interacao = Integer.parseInt(registro.substring(2,5));
                    tipo = registro.substring(5,15);
                    if (Integer.parseInt(registro.substring(5,15)) == 0){
                        like = true;
                    }else{
                        like = false;
                    }
                    fk_usuario = registro.substring(15,16);
                    fk_Publicacao = registro.substring(15,16);
                    texto = registro.substring(55,63);

                    Interacao interacao = new Interacao(id_Interacao,fk_usuario,fk_Publicacao,tipo,texto,like);
                    listaInteracao.add(interacao);

                    contRegistro++;
                }
                else if (tipoRegistro.equals("03")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
    }

}
