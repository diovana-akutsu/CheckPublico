package br.com.bandtec.piteste.gravarArquivo;

import br.com.bandtec.piteste.dominio.Obra;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

public class ArquivosCsv2 {

    public static void gravaDados(List<Obra> lista) {
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

}
