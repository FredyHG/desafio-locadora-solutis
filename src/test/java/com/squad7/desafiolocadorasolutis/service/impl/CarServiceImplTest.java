package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import com.squad7.desafiolocadorasolutis.exception.CarAlreadyRegisteredException;
import com.squad7.desafiolocadorasolutis.exception.CarModelNotFoundException;
import com.squad7.desafiolocadorasolutis.exception.CarNotAvailableException;
import com.squad7.desafiolocadorasolutis.exception.CarNotFoundException;
import com.squad7.desafiolocadorasolutis.factory.AccessoryFactory;
import com.squad7.desafiolocadorasolutis.factory.CarFactory;
import com.squad7.desafiolocadorasolutis.factory.CarModelFactory;
import com.squad7.desafiolocadorasolutis.mappers.CarMapper;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.repository.CarRepository;
import com.squad7.desafiolocadorasolutis.service.AccessoryService;
import com.squad7.desafiolocadorasolutis.service.CarModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarModelService carModelService;

    @Mock
    private AccessoryService accessoryService;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testSave_NewCar() {
        CarPostRequest carPostRequest = CarFactory.validCarPostRequest();
        List<Accessory> listAccessorySpy = spy(new ArrayList<>());

        when(accessoryService.findAllById(listAccessorySpy)).thenReturn(List.of(AccessoryFactory.createValidAccessory()));
        when(carModelService.findById(any(UUID.class))).thenReturn(CarModelFactory.createValidCarModel());

        carService.registerCar(carPostRequest);

        ArgumentCaptor<Car> carArgumentCaptor = ArgumentCaptor.forClass(Car.class);
        verify(carRepository, times(1)).save(carArgumentCaptor.capture());
        var carCapturedValue = carArgumentCaptor.getValue();

        assertNotNull(carCapturedValue.getDriveType());
        assertNotNull(carCapturedValue.getColor());
    }

    @Test
    void testThrowsException_WhenCarAlreadyRegistered() {
        CarPostRequest carPostRequest = CarFactory.validCarPostRequest();

        Car carAlreadyRegistered = CarFactory.validCar();

        when(carRepository.findByChassis(any(String.class))).thenReturn(Optional.of(carAlreadyRegistered));

        CarAlreadyRegisteredException exception = assertThrows(CarAlreadyRegisteredException.class, () -> carService.registerCar(carPostRequest));

        String expectedMessage = "Car already registered with chassis: " + carPostRequest.getChassis();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(carRepository, times(1)).findByChassis(carPostRequest.getChassis());
    }

    @Test
    void testThrowsException_WhenCarModelNotFound() {
        CarPostRequest carPostRequest = CarFactory.validCarPostRequest();

        when(carModelService.findById(any(UUID.class))).thenThrow(new CarModelNotFoundException("Car Model not found"));

        CarModelNotFoundException exception = assertThrows(CarModelNotFoundException.class, () -> carService.registerCar(carPostRequest));

        String expectedMessage = "Car Model not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetAllCarsFiltered() {
        List<Car> expectedCars = CarFactory.withSize(2);

        CategoryEnum categoryEnum = CategoryEnum.BID_SEDAN;
        List<String> idsAccessories = Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        CarRentalStatusEnum carRentalStatus = CarRentalStatusEnum.BOOKED;


        when(carRepository.getAllCarsFiltered(categoryEnum, idsAccessories, carRentalStatus)).thenReturn(expectedCars);

        List<Car> actualCars = carService.getAllCarsFiltered(categoryEnum, idsAccessories, carRentalStatus);

        assertEquals(expectedCars, actualCars);
        verify(carRepository, times(1)).getAllCarsFiltered(categoryEnum, idsAccessories, carRentalStatus);
    }

    @Test
    public void testEnsureCarExistsById_CarExists() {
        UUID carId = UUID.randomUUID();
        Car expectedCar = CarFactory.validCar();
        when(carRepository.findById(carId)).thenReturn(Optional.of(expectedCar));

        Car actualCar = carService.ensureCarExistsById(carId);

        assertEquals(expectedCar, actualCar);
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    public void testEnsureCarExistsById_CarDoesNotExist() {
        UUID carId = UUID.randomUUID();
        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        CarNotFoundException exception = assertThrows(CarNotFoundException.class, () -> {
            carService.ensureCarExistsById(carId);
        });

        assertEquals("No car found by id: " + carId, exception.getMessage());
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    public void testEnsureCarAvailableByPeriod_CarNotAvailable() {
        UUID carId = UUID.randomUUID();
        LocalDate startRental = LocalDate.of(2023, 10, 1);
        LocalDate endRental = LocalDate.of(2023, 10, 10);
        Car car = CarFactory.validCar();

        when(carRepository.findByCarUuidAndRentalPeriod(carId, startRental, endRental)).thenReturn(Optional.of(car));

        CarNotAvailableException exception = assertThrows(CarNotAvailableException.class, () -> {
            carService.ensureCarAvailableByPeriod(carId, startRental, endRental);
        });

        assertEquals("Car not available from " + startRental + " to " + endRental, exception.getMessage());
        verify(carRepository, times(1)).findByCarUuidAndRentalPeriod(carId, startRental, endRental);
    }

    @Test
    public void testEnsureCarAvailableByPeriod_CarAvailable() {
        UUID carId = UUID.randomUUID();
        LocalDate startRental = LocalDate.of(2024, 10, 1);
        LocalDate endRental = LocalDate.of(2024, 10, 10);

        when(carRepository.findByCarUuidAndRentalPeriod(carId, startRental, endRental)).thenReturn(Optional.empty());

        carService.ensureCarAvailableByPeriod(carId, startRental, endRental);

        verify(carRepository, times(1)).findByCarUuidAndRentalPeriod(carId, startRental, endRental);
    }

    @Test
    public void testEnsureCarNotRegisteredByChassis_CarAlreadyRegistered() {
        String chassis = "123ABC";
        Car car = CarFactory.validCar();
        when(carRepository.findByChassis(chassis)).thenReturn(Optional.of(car));

        CarAlreadyRegisteredException exception = assertThrows(CarAlreadyRegisteredException.class, () -> {
            carService.ensureCarNotRegisteredByChassis(chassis);
        });
        assertEquals("Car already registered with chassis: " + chassis, exception.getMessage());
        verify(carRepository, times(1)).findByChassis(chassis);
    }

    @Test
    public void testEnsureCarNotRegisteredByChassis_CarNotRegistered() {
        String chassis = "123ABC";
        when(carRepository.findByChassis(chassis)).thenReturn(Optional.empty());

        carService.ensureCarNotRegisteredByChassis(chassis);

        verify(carRepository, times(1)).findByChassis(chassis);
    }

    @Test
    public void testGetCarByUuid_CarExists() {

        UUID carId = UUID.randomUUID();
        Car car = CarFactory.validCar();
        CarResponse carResponse = CarFactory.validCarResponseByCar(car);

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carMapper.modelToResponse(car)).thenReturn(carResponse);

        CarResponse actualResponse = carService.getCarByUuid(carId);

        assertTrue(carResponse.getChassis().contains(actualResponse.getChassis()));
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    public void testGetCarByUuid_CarDoesNotExist() {
        UUID carId = UUID.randomUUID();
        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        CarNotFoundException exception = assertThrows(CarNotFoundException.class, () -> {
            carService.getCarByUuid(carId);
        });

        assertEquals("No cars found by id: " + carId, exception.getMessage());
        verify(carRepository, times(1)).findById(carId);
        verify(carMapper, times(0)).modelToResponse(any());
    }
}