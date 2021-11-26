package br.com.bandtec.piteste.gravarArquivo;

import br.com.bandtec.piteste.dominio.Obra;
import br.com.bandtec.piteste.repositorio.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArquivosTxt {

    private List<Obra> listaObra = new ArrayList<>();

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

    public static void gerarArquivoHeaderCorpoTrailer(List<Obra> lista){
        String nomeArq ="Obras.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dateDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            header += "00";
            header += "OBRAS";
            header += formatter.format(dateDeHoje);
            header += "10";

            //gravando registro no header
            gravarRegistro(nomeArq, header);

            //informações de coluna txt
//            corpo += String.format("%29s%20s%12s", " NOME ", " CATEGORIA ", " CEP ");


        for (int i = 0; i < lista.size(); i++){
            Obra a = lista.get(i);
            corpo += "01";

            corpo += String.format("%03d", a.getId_obra());
            corpo += String.format("%-30s", a.getNome());
            corpo += String.format("%-20s", a.getCategoria());
            corpo += String.format("%-8s", a.getCep());
            corpo += String.format("%-30s", a.getRua());
            corpo += String.format("%-5s", a.getNumero());
            corpo += String.format("%-18s", a.getBairro());
            corpo += String.format("%-20s", a.getCidade());
            corpo += String.format("%-2s", a.getEstado());
            corpo += String.format("%08.4f", a.getLatitude());
            corpo += String.format("%08.4f", a.getLongitude());
            contRegDados++;
            gravarRegistro(nomeArq, corpo);
            corpo = "";
        }

            trailer += "02";
            trailer += String.format("%05d", contRegDados);
            gravarRegistro(nomeArq, trailer);

    }

    /* Fim Gravar Arquivo */
    /*******************************************************************************************************/
    /* Inicio Ler Arquivo */

    public static void leArquivo(String nomeArq) {


        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nome, categoria, cep, rua, numero, bairro, cidade, estado;
        double latitudo, longitude;
        Integer id_obra;
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
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-3s %-30s %-20s %-8s %-30s %-5s %-18s %-20s %-2s %-8s %-8s\n",
                                "ID","nome","categoria","cep", "rua", "numero", "bairro",
                                "cidade", "estado", "latitudo", "longitude");
                    }

                    id_obra = Integer.parseInt(registro.substring(2,5));
                    nome = registro.substring(5,35);
                    categoria = registro.substring(35,55);
                    cep = registro.substring(55,63);
                    rua = registro.substring(63,93);
                    numero = registro.substring(93,98);
                    bairro = registro.substring(98,116);
                    cidade = registro.substring(116,136);
                    estado = registro.substring(136,138);
                    latitudo = Double.parseDouble(registro.substring(138,146).replace(',','.'));
                    longitude = Double.parseDouble(registro.substring(146,154).replace(',','.'));

                    Obra obra = new Obra(id_obra,nome,categoria,cep,rua,numero,bairro
                            ,cidade,estado,latitudo,longitude);

                    System.out.printf("%3d %-30s %-20s %-8s %-30s %-5s %-18s %-20s %2s %08.4f %08.4f\n",
                            id_obra, nome, categoria,cep, rua, numero,bairro,cidade,estado,latitudo,longitude);
                    contRegistro++;
                }
                else if (tipoRegistro.equals("02")) {
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
