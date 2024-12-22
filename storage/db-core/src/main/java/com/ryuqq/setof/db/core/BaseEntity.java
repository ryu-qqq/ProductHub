package com.ryuqq.setof.db.core;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "DELETE_YN", nullable = false)
    protected boolean deleteYn;

    @CreationTimestamp
    @Column(name = "INSERT_TIME", nullable = false)
    private LocalDateTime insertTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_TIME", nullable = false)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public boolean isDeleteYn() {
        return deleteYn;
    }

    protected void delete(){
        this.deleteYn = true;
    }


}
