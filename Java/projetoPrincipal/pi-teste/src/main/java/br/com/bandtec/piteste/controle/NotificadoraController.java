package br.com.bandtec.piteste.controle;

import br.com.bandtec.piteste.config.security.TokenService;
import br.com.bandtec.piteste.dominio.Email;
import br.com.bandtec.piteste.dominio.Notificados;
import br.com.bandtec.piteste.dominio.Obra;
import br.com.bandtec.piteste.dominio.Usuario;
import br.com.bandtec.piteste.model.PilhaObj;
import br.com.bandtec.piteste.repositorio.NotificadosRepository;
import br.com.bandtec.piteste.repositorio.ObraRepository;
import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificados")
public class NotificadoraController {

    @Autowired
    private NotificadosRepository notificadosRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObraRepository obraRepository;


    PilhaObj aSeremNotificados = new PilhaObj(100);
    PilhaObj notificados = new PilhaObj(100);

    @GetMapping
    public ResponseEntity notificarUsuario() {

        List<Notificados> n = notificadosRepository.findAll();
        if (n.isEmpty()) {
            return ResponseEntity.status(204).body("Lista de notificados vazia");
        } else {
            System.out.println("teste:" + n);
            for (int i = 0; i < n.size(); i++) {

                aSeremNotificados.push(n.get(i));
                aSeremNotificados.exibe();
                Email email = new Email(n.get(i).getUsuario().getEmail(),
                        definirTexto(n.get(i).getUsuario().getNome(),
                                n.get(i).getObra().getNome()));
                enviar(email.getEmail(), email.getTexto());
                notificados.push(aSeremNotificados.pop());
            }

            return ResponseEntity.status(200).build();
        }

    }


    //@GetMapping("/especifico/{obra}")
    public ResponseEntity notificarUsuarioObra(@PathVariable Integer obra) {

        List<Notificados> n = notificadosRepository.findAllByObra_Id(obra);
        if (n.isEmpty()) {
            return ResponseEntity.status(204).body("Lista de notificados vazia");
        } else {

            System.out.println("teste:" + n);
            for (int i = 0; i < n.size(); i++) {

                aSeremNotificados.push(n.get(i));
                aSeremNotificados.exibe();
                Email email = new Email(n.get(i).getUsuario().getEmail(),
                        definirTexto(n.get(i).getUsuario().getNome(),
                                n.get(i).getObra().getNome()));
                enviar(email.getEmail(), email.getTexto());
                notificados.push(aSeremNotificados.pop());
            }

            return ResponseEntity.status(200).build();
        }

    }


    @GetMapping("/usuario")
    public ResponseEntity buscarObrasPorUsuario() {
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();

        List<Notificados> n = notificadosRepository.findAllByUsuario_Id(usuario.getId_usuario());

        if (n.isEmpty()) {
            return ResponseEntity.status(204).body("Lista de notificados vazia");
        } else {
            return ResponseEntity.status(200).body(n);
        }
    }

    @PostMapping("/{obra}")
    public ResponseEntity adicionarUsuario(@PathVariable Integer obra) {
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();

        Optional<Notificados> notificado = notificadosRepository.findByUsuario_IdAndObra_Id(usuario.getId_usuario(), obra);
        if (notificado.isPresent()) {
            notificadosRepository.deleteById(notificado.get().getId_notificado());
            return ResponseEntity.status(200).build();
        } else {
            Usuario usuarioEncontrado = usuarioRepository.findById(tokenService.getIdResgatado()).get();
            Obra obraEncontrado = obraRepository.findById(obra).get();
            notificadosRepository.save(new Notificados(usuarioEncontrado, obraEncontrado));
            return ResponseEntity.status(201).build();
        }

    }

    @DeleteMapping("/{obra}")
    public ResponseEntity removerUsuario(@PathVariable Integer obra) {
        Usuario usuario = usuarioRepository.findById(tokenService.getIdResgatado()).get();

        Optional<Notificados> notificado = notificadosRepository.findByUsuario_IdAndObra_Id(usuario.getId_usuario(), obra);
        if (notificado.isPresent()) {
            notificadosRepository.deleteById(notificado.get().getId_notificado());
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).body("Notificação não existe");
        }
    }

    public String definirTexto(String nome, String obra) {
        String texto = "Prezado," + nome + "\n\nA obra '" + obra + "' possui uma nova publicação.\n" +
                "Para vê-la acesse nosso site.\nhttps://check-web.azurewebsites.net/\n" +
                "\nAgradeço o interesse.\n" +
                "Até a próxima! ;-)";
        return texto;
    }

    public String enviar(String email, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(texto);
        message.setSubject("Nova publicação");
        message.setTo(email);
        message.setFrom("rafael30s@hotmail.com.br");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
