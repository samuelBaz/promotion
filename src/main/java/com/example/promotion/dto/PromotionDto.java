package com.example.promotion.dto;

import com.example.promotion.model.Promotion;

import java.time.LocalDate;
import java.util.Date;

public class PromotionDto extends DtoBase<Promotion> {

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