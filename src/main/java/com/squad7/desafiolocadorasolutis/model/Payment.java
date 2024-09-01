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
    @Column(nullable = false, name = "payment_method")
    private String paymentMethod;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @Column(nullable = false, name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "payment_date")
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
