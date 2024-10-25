package com.ryuqq.setof.storage.db.core;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DELETE_YN", nullable = false)
    private boolean deleteYn;

    @CreationTimestamp
    @Column(name = "INSERT_DATE", nullable = false)
    private LocalDateTime insertTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE", nullable = false)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }


}
