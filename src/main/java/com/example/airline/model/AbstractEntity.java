package com.example.airline.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "created", updatable = false)
    public LocalDateTime created;

    @Column(name = "updated", updatable = false)
    public LocalDateTime updated;


    @PrePersist
    public void toCreate(){
        setCreated(LocalDateTime.now()); }


    @PreUpdate
    public void toUpdate(){
        setUpdated(LocalDateTime.now());}


    public AbstractEntity() {
    }

    public AbstractEntity(Long id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
