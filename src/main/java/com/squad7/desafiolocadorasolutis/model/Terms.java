package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.TermsStatus;
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
    private TermsStatus termsStatus;
    private LocalDateTime acceptAt;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver acceptBy;

    public Terms(Driver driver) {
        this.acceptBy = driver;
        this.termsStatus = TermsStatus.TO_ACCEPT;
        this.acceptAt = null;
    }

    public Terms acceptTerms() {
        this.termsStatus = TermsStatus.ACCEPTED;
        this.acceptAt = LocalDateTime.now();

        return this;
    }

    protected Terms() {
    }
}
