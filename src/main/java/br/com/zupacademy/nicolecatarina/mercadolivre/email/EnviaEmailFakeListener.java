package br.com.zupacademy.nicolecatarina.mercadolivre.email;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class EnviaEmailFakeListener {

    @Async
    @EventListener
    public void enviarEmail(Email email) {
        System.out.println("Corpo do Email: " + email.getTitulo());
        System.out.println("De: " + email.getRemetente());
        System.out.println("Para: " + email.getDestinatario());
        System.out.println("---------------------------------------------------------------------------------");
    }

}
