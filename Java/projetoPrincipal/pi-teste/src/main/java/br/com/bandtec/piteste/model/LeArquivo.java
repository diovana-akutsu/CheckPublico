package br.com.bandtec.piteste.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import br.com.bandtec.piteste.controle.dto.UsuarioDTO;
import br.com.bandtec.piteste.dominio.Interacao;
import br.com.bandtec.piteste.dominio.Publicacao;
import br.com.bandtec.piteste.dominio.Usuario;
import br.com.bandtec.piteste.repositorio.InteracaoRepository;
import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LeArquivo {

    public void leArquivo(String nomeArq, UsuarioRepository usuarioRepository, InteracaoRepository interacaoRepository) {

        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        UsuarioDTO usuario=new UsuarioDTO();
        Interacao interacao= new Interacao();
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

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 13));
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(13,33));
                    System.out.println("Versão do layout: " + registro.substring(33,34));
                }
                else if (tipoRegistro.equals("03")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,7));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else if (tipoRegistro.equals("01")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-5s %-6s %-7s\n", "NOME","EMAIL","SENHA");

                    }

                    usuario.setNome(registro.substring(2,32).trim());
                    usuario.setEmail(registro.substring(32,82).trim());
                    usuario.setSenha(registro.substring(82,142).trim());
                    Optional<Usuario> usuarioVerificacao=usuarioRepository.findByEmail(usuario.getEmail());
                    if (usuarioVerificacao.isPresent()){
                        System.out.println("Email '"+ usuario.getEmail()+"' já cadastrado");
                    }else{
                     //   usuarioRepository.save(usuario);
                        String novaSenha = new BCryptPasswordEncoder().encode(usuario.getSenha());
                        usuario.setSenha(novaSenha);
                        usuarioRepository.save(new Usuario(usuario.getNome(),usuario.getEmail(),usuario.getSenha()));
                    }



                    System.out.printf("%-55s %-25s %-25s\n", usuario.getNome(), usuario.getEmail(), usuario.getSenha());

                    contRegistro++;
                } else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-5s %-6s %-10s %-15s %-15s %-15s %-20s %-15s %-20s\n", "TIPO","LIKE","CARISMA", "ID_USUARIO", "ID_PUBLICACAO", "TEXTO");

                    }

                    // personagem.setId(Integer.valueOf(registro.substring(2,5)));
                    interacao.setTipo(registro.substring(2,12).trim());
                    interacao.setCurtir(Boolean.valueOf(registro.substring(12,14)));
                    interacao.setUsuario(new Usuario());
                    interacao.getUsuario().setId_usuario(Integer.valueOf(registro.substring(14,17)));
                    interacao.setPublicacao(new Publicacao());
                    interacao.getPublicacao().setId_publicacao(Integer.valueOf(registro.substring(17,20)));
                    interacao.setComentario(registro.substring(21,120));




                    interacaoRepository.save(interacao);


                    System.out.printf("%-15s %2s %2d %2d %100s\n", interacao.getTipo(), interacao.getCurtir(),
                            interacao.getUsuario().getId_usuario(), interacao.getPublicacao().getId_publicacao(),interacao.getComentario());
                    contRegistro++;
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
