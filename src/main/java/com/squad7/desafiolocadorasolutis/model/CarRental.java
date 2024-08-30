package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table(name = "tb_car_rental")
@Entity
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "rent_date")
    private LocalDateTime rentDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "insurance_policy_id")
    private InsurancePolicy insurancePolicy;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rental_terms_id")
    private Terms rentalTerms;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public CarRental(Car car, InsurancePolicy insurancePolicy, Driver driver, BigDecimal price, LocalDateTime returnDate, LocalDateTime rentDate) {
        this.car = car;
        this.insurancePolicy = insurancePolicy;
        this.price = price;
        this.returnDate = returnDate;
        this.rentDate = rentDate;
        this.driver = driver;
        this.rentalTerms = new Terms(driver);
    }

    protected CarRental() {
    }
}
