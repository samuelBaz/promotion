/**
 * @author: Samuel Bazoalto
 */

package com.example.promotion.model;

import com.example.promotion.dto.PromotionDto;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Promotion extends ModelBase<PromotionDto> {

    private String description;
    private LocalDate durationDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDurationDate() {
        return durationDate;
    }

    public void setDurationDate(LocalDate durationDate) {
        this.durationDate = durationDate;
    }
}