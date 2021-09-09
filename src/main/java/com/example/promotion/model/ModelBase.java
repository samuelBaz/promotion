/**
 * @author: Samuel Bazoalto
 */
package com.example.promotion.model;

import com.example.promotion.dto.DtoBase;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("rawtypes")
public class ModelBase <D extends DtoBase> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdOn;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date updatedOn;

    @Version
    @Column(nullable = false)
    private long version;

    public Long getIndexedId() {
        if (null == id) {
            return 0L;
        }
        return id;
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

    protected void beforeConversion(D element, ModelMapper mapper) {
        // Do nothing
    }

    protected void afterConversion(D element, ModelMapper mapper) {
        // Do nothing
    }

    @SuppressWarnings("unchecked")
    public <E extends ModelBase> E toDomain(D element, ModelMapper mapper) {
        try {
            return convert(element, mapper);
        } catch (Exception ex) {
            return (E) this;
        }
    }

    @SuppressWarnings("unchecked")
    protected <E extends ModelBase> E convert(D element, ModelMapper mapper) {
        beforeConversion(element, mapper);
        mapper.map(element, this);
        afterConversion(element, mapper);
        return (E) this;
    }

    public <E extends ModelBase> List<E> toListDomain(Collection<D> elements, ModelMapper mapper) {
        if (elements == null || elements.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return convert(elements, mapper);
        } catch (Exception ex) {
            return Collections.emptyList();
        }

    }

    @SuppressWarnings("unchecked")
    protected <E extends ModelBase> List<E> convert(Collection<D> elements, ModelMapper mapper) {
        return (List<E>) elements.stream().map(element -> {
            try {
                return this.getClass().newInstance().toDomain(element, mapper);
            } catch (InstantiationException | IllegalAccessException e) {
                return new ModelBase<>();
            }
        }).sorted(Comparator.comparing(ModelBase::getIndexedId)).collect(Collectors.toList());
    }
}