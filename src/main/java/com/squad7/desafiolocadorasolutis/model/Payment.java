package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Table(name = "tb_payment")
@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String paymentMethod;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;

    public Payment(LocalDateTime orderDate,
                   Driver driver, String paymentMethod) {
        this.orderDate = orderDate;
        this.driver = driver;
        this.paymentMethod = paymentMethod != null ? paymentMethod : "not informed";
    }

    protected Payment() {
    }
}
