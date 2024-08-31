package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.CarRentalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "rental_status")
    private CarRentalStatus rentalStatus;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "insurance_policy_id")
    private InsurancePolicy insurancePolicy;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rental_terms_id")
    private Terms rentalTerms;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public CarRental(Car car, InsurancePolicy insurancePolicy, Driver driver, BigDecimal price, LocalDate returnDate, LocalDate rentDate, Employee employee, String paymentType, CarRentalStatus rentalStatus) {
        this.car = car;
        this.insurancePolicy = insurancePolicy;
        this.price = price;
        this.returnDate = returnDate;
        this.rentDate = rentDate;
        this.driver = driver;
        this.employee = employee;
        this.rentalTerms = new Terms(driver);
        this.payment = new Payment(LocalDateTime.now(), driver, paymentType);
        this.rentalStatus = rentalStatus;
    }

    protected CarRental() {
    }

    public void calculateTotalPrice() {
        long period = Period.between(this.rentDate, this.returnDate).getDays();

        BigDecimal periodValue = this.car.getPricePerDay().multiply(BigDecimal.valueOf(period));

        this.insurancePolicy.calculatePolicyValue(periodValue);

        this.price = this.insurancePolicy.getTotalValue().add(periodValue);
    }
}
