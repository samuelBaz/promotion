/**
 * @author: Samuel Bazoalto
 */

package com.example.promotion.model;

import com.example.promotion.dto.ClientDto;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Client extends ModelBase<ClientDto> {

    private Long clientNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

    public Long getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Long clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}