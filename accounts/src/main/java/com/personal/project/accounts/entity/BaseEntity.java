package com.personal.project.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass //this tells SpringDataJPA that this class will act as a superclass for all the entities
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(updatable = false) //to make createdAt not updatable
    private LocalDateTime createdAt;

    @Column(insertable = false) //to not insert any value at the begining
    private LocalDateTime updatedAt;

    @Column(updatable = false) //to make createdBy not updatable
    private String createdBy;

    @Column(insertable = false)
    private String updatedBy;
}
