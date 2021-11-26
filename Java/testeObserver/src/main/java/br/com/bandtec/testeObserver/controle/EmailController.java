package br.com.bandtec.testeObserver.controle;

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
        message.setText("UI PAPAI TÔ ENVIANDO EMAIL LEK");
        message.setSubject("Teste de envio");
        message.setTo("rafael30s@hotmail.com.br");
        message.setFrom("211-3b-grupo2@bandtec.com.br");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
