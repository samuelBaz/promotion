package com.example.promotion;

/**
 * @author: Samuel Bazoalto
 */

import com.example.promotion.model.Client;
import com.example.promotion.services.ClientService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ClientService clientService;

    public DevelopmentBootstrap(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
        persistBuy();
    }

    private void persistBuy() {
        Client client1 = new Client();
        client1.setClientNumber(1L);
        client1.setFirstName("Samuel");
        client1.setLastName("Bazaolto");
        client1.setEmail("bazaoltosamuel33@gmail.com");
        client1.setDateOfBirth(LocalDate.of(1999,9,9));
        clientService.save(client1);

        Client client2 = new Client();
        client2.setClientNumber(2L);
        client2.setFirstName("Esteban");
        client2.setLastName("Marcos");
        client2.setEmail("esteban@gmail.com");
        client2.setDateOfBirth(LocalDate.of(1999,9,10));
        clientService.save(client2);
    }
}