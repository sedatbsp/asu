package com.sedatbsp.asu.infrastructure.common.entity;

import com.sedatbsp.asu.domain.common.model.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", length = 400)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", length = 400)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

}
