package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatus;
import com.squad7.desafiolocadorasolutis.mappers.CarRentalMapper;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.model.CarRental;
import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.model.Employee;
import com.squad7.desafiolocadorasolutis.repository.CarRentalRepository;
import com.squad7.desafiolocadorasolutis.repository.EmployeeRepository;
import com.squad7.desafiolocadorasolutis.service.CarRentalService;
import com.squad7.desafiolocadorasolutis.service.EmployeeService;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRentalServiceImpl implements CarRentalService {

    private final CarRentalRepository carRentalRepository;
    private final DriverServiceImpl driverService;
    private final CarServiceImpl carService;
    private final PaymentFacade paymentFacade;
    private final EmployeeService employeeService;

    @Override
    public void rentCar(CarRentalPostRequest carRental) {
        log.info("Starting car rental process for driver with CPF: {}", carRental.getDriverCpf());

        Driver driver = driverService.ensureDriverExistsByCpf(carRental.getDriverCpf());
        log.info("Driver found: CPF = {}, Name = {}", driver.getCpf(), driver.getName());

        driverService.ensureDriverAcceptAccountTermsByEmail(driver.getEmail());

        driverService.ensureDriverConfirmEmailTermsByEmail(driver.getEmail());

        Employee employee = employeeService.ensureEmployeeExistsByRegistration(carRental.getEmployeeRegistration());
        log.info("Employee found: Registration = {}, Name = {}", employee.getRegistration(), employee.getName());

        Car car = carService.ensureCarExistsById(carRental.getCarId());
        carService.ensureCarAvailableByPeriod(car.getId(), carRental.getRentDate(), carRental.getReturnDate());
        log.info("Car found: ID = {}, Chassis = {}", car.getId(), car.getChassis());

        CarRental savedCarRental = CarRentalMapper.INSTANCE.requestToModel(carRental);
        applyCarRentalSettings(savedCarRental, driver, employee, car);

        carRentalRepository.save(savedCarRental);

        log.info("Car rental process finished for driver with CPF: {}", carRental.getDriverCpf());
    }

    @Override
    public List<CarRentalResponse> getAllCarsFiltered(String cpf, List<CarRentalStatus> statusList){
        List<CarRentalResponse> responseList = new ArrayList<>();
        Driver driver = driverService.ensureDriverExistsByCpf(cpf);
        List<CarRental> modelList = carRentalRepository.findByDriverAndRentalStatusIn(driver, statusList);

        modelList.forEach( carRental -> {
            responseList.add(CarRentalMapper.INSTANCE.modelToResponse(carRental));
        });

        return responseList;
    }


    public void confirmRent(UUID carRentalId) {
        log.info("Starting rent confirmation process for driver with CPF: {}", carRentalId);

        CarRental carRental = ensureCarRentalExistsById(carRentalId);

        if(carRental.getRentalStatus() != CarRentalStatus.BOOKED) {
            throw new RuntimeException("Cannot confirm car rental: expected status 'BOOKED', but found '" + carRental.getRentalStatus() + "'.");
        }

        carRental.makePayment();

        paymentFacade.makePayment(carRental.getPayment().getPaymentMethod(), carRental.getPrice());
        carRental.setRentalStatus(CarRentalStatus.PAYMENT_SUCCESSFULLY);

        carRentalRepository.save(carRental);

        log.info("Starting rent confirmation process for driver with CPF: {}", carRentalId);
    }

    public void startRent(UUID rentId) {
        CarRental carRental = ensureCarRentalExistsById(rentId);

        if(carRental.getRentalStatus() != CarRentalStatus.PAYMENT_SUCCESSFULLY) {
            throw new RuntimeException("Cannot start car rental: expected status 'PAYMENT_SUCCESSFULLY', but found '" + carRental.getRentalStatus() + "'.");
        }

        carRental.setRentalStatus(CarRentalStatus.IN_PROGRESS);

        carRentalRepository.save(carRental);
    }

    public void finishRent(UUID rentId) {

        CarRental carRental = ensureCarRentalExistsById(rentId);

        if(carRental.getRentalStatus() != CarRentalStatus.IN_PROGRESS) {
            throw new RuntimeException("Cannot finish car rental: expected status 'IN_PROGRESS', but found '" + carRental.getRentalStatus() + "'.");
        }

        carRental.setRentalStatus(CarRentalStatus.FINISHED);

        carRentalRepository.save(carRental);
    }

    public void cancelRent(UUID rentId) {
        CarRental carRental = ensureCarRentalExistsById(rentId);

        if(carRental.getRentalStatus() != CarRentalStatus.BOOKED) {
            throw new RuntimeException("Cannot cancel car rental: expected status 'BOOKED', but found '" + carRental.getRentalStatus() + "'.");
        }

        carRental.setRentalStatus(CarRentalStatus.CANCELLED);

        carRentalRepository.save(carRental);
    }

    public CarRental ensureCarRentalExistsById(UUID carRentalId) {
        return findById(carRentalId).orElseThrow(() -> new RuntimeException("CarRental not found"));
    }

    public Optional<CarRental> findById(UUID carRentalId) {
        return carRentalRepository.findById(carRentalId);
    }

    private void applyCarRentalSettings(CarRental savedCarRental, Driver driver, Employee employee, Car car) {
        savedCarRental.setRentalStatus(CarRentalStatus.BOOKED);
        savedCarRental.setEmployee(employee);
        savedCarRental.setDriver(driver);
        savedCarRental.getPayment().setDriver(driver);
        savedCarRental.setCar(car);
        savedCarRental.getRentalTerms().setAcceptBy(driver);
        savedCarRental.calculateTotalPrice();
    }
}


