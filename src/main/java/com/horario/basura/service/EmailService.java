/**
 * 
 */
package com.horario.basura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String destinatario, String name) {
        SimpleMailMessage email = new SimpleMailMessage();
        // aca es donde se envia a los destinatarios
        email.setTo(destinatario);
        email.setSubject("Preparate a sacar la  basura");
        email.setText("Recuerda " + " "+ name+" " + " que eres el proximo en sacar la basura");
        mailSender.send(email);
    }
}
