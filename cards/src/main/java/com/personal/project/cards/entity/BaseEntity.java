package com.personal.project.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //this tells SpringDataJPA that this class will act as a superclass for all the entities
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class) //to populate the data by Spring
public class BaseEntity {

    @CreatedDate // Spring will populate the date itself
    @Column(updatable = false) //to make createdAt not updatable
    private LocalDateTime createdAt;

    @LastModifiedDate // Spring will populate the date itself
    @Column(insertable = false) //to not insert any value at the beginning
    private LocalDateTime updatedAt;

    @CreatedBy // Spring will populate the date itself using AuditAwareImpl class we created
    @Column(updatable = false) //to make createdBy not updatable
    private String createdBy;

    @LastModifiedBy // Spring will populate the date itself using AuditAwareImpl class we created
    @Column(insertable = false)
    private String updatedBy;
}
