package br.com.bandtec.piteste.gravarArquivo;

import br.com.bandtec.piteste.dominio.Obra;
import br.com.bandtec.piteste.model.ListaObj;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArquivosCsv {

    public static void gravaObras(List<Obra> lista) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        String nomeArquivo;

        nomeArquivo = "Obras.csv";

        try {
            arq = new FileWriter(nomeArquivo, true);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }
        try {
            for (int i=0; i< lista.size(); i++) {
                Obra a = lista.get(i);

                saida.format("%d;%s;%s;%s;%s;%s;%s;%s;%s;%.2f;%.2f%n",a.getId_obra(),a.getNome(),a.getCategoria(),a.getCep(),
                        a.getRua(),a.getNumero(),a.getBairro(),a.getCidade(),a.getEstado(),
                        a.getLatitude(),a.getLongitude());


            }
        }
        catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim= true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    /* Fim Gravar Arquivo */
    /*******************************************************************************************************/
    /* Inicio Ler Arquivo */

    public static void lerObras() {
        FileReader arq= null;
        Scanner entrada = null;
        String nomeArquivo;
        boolean deuRuim= false;

        nomeArquivo= "Obras.csv";

        // Abre o arquivo para leitura
        try {
            arq = new FileReader(nomeArquivo);

            entrada = new Scanner(arq).useDelimiter(";|\\r\\n");
        }
        catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%-3s%-30s%-20s%-8s%-30s%-5s%-18s%-20s%-10s%-12s%-8s\n"
                    ,"ID","NOME","CATEGORIA","CEP","RUA","NUMERO","BAIRRO","CIDADE","ESTADO","LATITUDE","LONGITUDE" );

            while (entrada.hasNext()) {
                Integer id_obra = entrada.nextInt();
                String nome = entrada.next();
                String categoria = entrada.next();
                String cep = entrada.next();
                String rua = entrada.next();
                String numero = entrada.next();
                String bairro = entrada.next();
                String cidade = entrada.next();
                String estado = entrada.next();
                double latitude = entrada.nextDouble();
                double longitude = entrada.nextDouble();
                System.out.printf("%-3d%-30s%-20s%-8s%-30s%-5s%-18s%-20s%-10s%-12.2f%-8.2f\n",
                        id_obra,nome,categoria,cep,rua,numero,bairro,cidade,
                        estado,latitude,longitude);
            }
        }
        catch (NoSuchElementException erro)
        {
            System.err.println("Arquivo com problemas.");
            deuRuim = true;
        }
        catch (IllegalStateException erro)
        {
            System.err.println("Erro na leitura do arquivo.");
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
