package br.com.bandtec.piteste.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/enviar")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Boa tarde!\n " +
                "Fulano, a obra 'Hospital X' possui uma nova publicação.\n" +
                "Para vê-la acesse link-que-vou-ver-como-gerar.com\n" +
                "Agradeço o interesse.\n" +
                "Até a próxima! ;-)");
        message.setSubject("Teste de envio");
        message.setTo("rafael30s@hotmail.com.br");
        message.setFrom("wizard.leafar@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
