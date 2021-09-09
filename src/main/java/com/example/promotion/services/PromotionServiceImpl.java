/**
 * @author: Samuel Bazaolto
 */

package com.example.promotion.services;

import com.example.promotion.model.Client;
import com.example.promotion.model.Promotion;
import com.example.promotion.repository.PromotionRepository;
import com.example.promotion.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionServiceImpl extends GenericServiceImpl<Promotion> implements PromotionService {

    @Value("${discountValue}")
    private BigDecimal discountValue;
    private final PromotionRepository repository;
    private ClientServiceImpl clientService;

    public PromotionServiceImpl(PromotionRepository repository, ClientServiceImpl clientService) {
        this.repository = repository;
        this.clientService = clientService;
    }

    public void savePromotionsToAllClientsWithBirthNow(){
        LocalDate date = LocalDate.now();
        List<Client> clients = clientService.getClientsByDateOfBirth(date);
        for (Client client : clients){
            String description = createMessageToPromotion(client);
            Promotion promotion = new Promotion();
            promotion.setDescription(description);
            promotion.setDurationDate(client.getDateOfBirth());
            repository.save(promotion);
        }
    }

    private String createMessageToPromotion(Client client) {
        StringBuilder message = new StringBuilder();
        message.append(client.getFirstName()+" Hoy es su cumpleaños y usted es importante para nosotros, " +
                " queremos celebralo ofreciendo un "+ discountValue + " de descuento y delivery gratuito. Valido por 24 hrs");
        return message.toString();
    }

    @Override
    protected GenericRepository<Promotion> getRepository() {
        return repository;
    }
}
