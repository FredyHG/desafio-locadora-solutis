package com.squad7.desafiolocadorasolutis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.squad7.desafiolocadorasolutis.enums.TermsStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table(name = "tb_terms")
@Entity
public class Terms {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, name = "terms_status_enum")
    private TermsStatusEnum termsStatusEnum;
    @Column(name = "accept_at")
    private LocalDateTime acceptAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver acceptBy;

    public Terms(Driver driver) {
        this.acceptBy = driver;
        this.termsStatusEnum = TermsStatusEnum.TO_ACCEPT;
        this.acceptAt = null;
    }

    public Terms acceptTerms() {
        this.termsStatusEnum = TermsStatusEnum.ACCEPTED;
        this.acceptAt = LocalDateTime.now();

        return this;
    }

    protected Terms() {
    }
}
