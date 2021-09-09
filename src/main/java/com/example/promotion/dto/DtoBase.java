/**
 * @author: Samuel Bazoalto
 */
package com.example.promotion.dto;

import com.example.promotion.model.ModelBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.promotion.model.ModelBase;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class DtoBase <E extends ModelBase> {

    protected Logger logger = LoggerFactory.getLogger(DtoBase.class);

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedOn;
    private long version;

    protected void beforeConversion(E element, ModelMapper mapper) {
        // Do nothing
    }

    protected void afterConversion(E element, ModelMapper mapper) {
        // Do nothing
    }

    @SuppressWarnings("unchecked")
    public <D extends DtoBase> D toDto(E element, ModelMapper mapper) {
        if (element == null) {
            return (D) this;
        }
        return convert(element, mapper);
    }

    @SuppressWarnings("unchecked")
    protected <D extends DtoBase> D convert(E element, ModelMapper mapper) {
        beforeConversion(element, mapper);
        try {
            mapper.map(element, this);
        } catch (Exception ex) {
            setId(element.getId());
            logger.error("Error mapping", ex);
            return (D) this;
        }
        afterConversion(element, mapper);
        return (D) this;
    }

    public <D extends DtoBase> List<D> toListDto(Collection<E> elements, ModelMapper mapper) {
        if (elements == null || elements.isEmpty()) {
            return Collections.emptyList();
        }
        return convert(elements, mapper);
    }

    @SuppressWarnings("unchecked")
    protected <D extends DtoBase> List<D> convert(Collection<E> elements, ModelMapper mapper) {
        return (List<D>) elements.stream().map(element -> {
            try {
                return this.getClass().newInstance().toDto(element, mapper);
            } catch (InstantiationException | IllegalAccessException e) {
                return new DtoBase<>();
            }
        }).sorted(Comparator.comparing(DtoBase::getId)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
