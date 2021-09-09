package com.example.promotion.services;

import com.example.promotion.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

@SpringBootTest
public class ClientServiceImplTest {

    @Autowired
    ClientServiceImpl clientService;

    @Test
    public void test(){
        LocalDate todayDate = LocalDate.now();
        List<Client> clients = clientService.getClientsByDateOfBirth(todayDate);
        assertEquals(1,clients.size());
    }
}