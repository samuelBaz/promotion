/**
 * @author: Samuel Bazoalto
 */

package com.example.promotion.repository;

import com.example.promotion.model.Client;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends GenericRepository<Client> {

    List<Client> getAllByDateOfBirth(LocalDate birth);
}