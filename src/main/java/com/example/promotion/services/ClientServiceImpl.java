/**
 * @author: Samuel Bazaolto
 */

package com.example.promotion.services;

import com.example.promotion.model.Client;
import com.example.promotion.repository.ClientRepository;
import com.example.promotion.repository.GenericRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client> implements ClientService {
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getClientsByDateOfBirth(LocalDate date){
        List<Client> result = new ArrayList<>();
        List<Client> clients = repository.findAll();
        for(Client client : clients){
            LocalDate birth = client.getDateOfBirth();
            if(birth.getMonth() == date.getMonth() && birth.getDayOfMonth() == date.getDayOfMonth()){
                result.add(client);
            }
        }
        return result;
    }

    @Override
    protected GenericRepository<Client> getRepository() {
        return repository;
    }
}
