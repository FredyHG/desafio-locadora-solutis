package com.squad7.desafiolocadorasolutis.config;

import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.enums.Category;
import com.squad7.desafiolocadorasolutis.enums.Sex;
import com.squad7.desafiolocadorasolutis.model.*;
import com.squad7.desafiolocadorasolutis.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CarModelRepository carModelRepository;
    private final AccessoryRepository accessoryRepository;
    private final DriverRepository driverRepository;
    private final EmployeeRepository employeeRepository;
    private final CarRentalRepository carRentalRepository;

    @Override
    public void run(String... args) {
        if (manufacturerRepository.findAll().isEmpty()) {
            List<Manufacturer> manufacturers = createAndSaveManufacturers();
            List<CarModel> carModels = createAndSaveCarModels(manufacturers);
            List<Accessory> accessories = createAndSaveAccessories();
            List<Car> cars = createAndSaveCars(carModels, accessories);
            List<Driver> drivers = createAndSaveDrivers();
            List<Employee> employees = createAndSaveEmployees();
            createAndSaveCarRentals(cars, drivers, employees);
        }
    }

    private List<Manufacturer> createAndSaveManufacturers() {
        Manufacturer manufacturer1 = manufacturerRepository.save(new Manufacturer("Manufacturer X"));
        Manufacturer manufacturer2 = manufacturerRepository.save(new Manufacturer("Manufacturer Y"));
        Manufacturer manufacturer3 = manufacturerRepository.save(new Manufacturer("Manufacturer Z"));
        return List.of(manufacturer1, manufacturer2, manufacturer3);
    }

    private List<CarModel> createAndSaveCarModels(List<Manufacturer> manufacturers) {
        CarModel compactHatchX = carModelRepository.save(new CarModel("Compact Hatchback X", Category.COMPACT_HATCH, manufacturers.get(0)));
        CarModel mediumSedanY = carModelRepository.save(new CarModel("Medium Sedan Y", Category.MEDIUM_SEDAN, manufacturers.get(1)));
        CarModel sportyZ = carModelRepository.save(new CarModel("Sporty Z", Category.SPORT, manufacturers.get(2)));
        CarModel minivanV = carModelRepository.save(new CarModel("Minivan V", Category.MINIVAN, manufacturers.get(1)));
        CarModel compactSedanW = carModelRepository.save(new CarModel("Compact Sedan W", Category.COMPACT_SEDAN, manufacturers.get(0)));
        return List.of(compactHatchX, mediumSedanY, sportyZ, minivanV, compactSedanW);
    }

    private List<Accessory> createAndSaveAccessories() {
        Accessory blueToothStereo = accessoryRepository.save(new Accessory("Bluetooth Stereo", "Bluetooth-enabled stereo system"));
        Accessory leatherSeats = accessoryRepository.save(new Accessory("Leather Seats", "High-quality leather seats for comfort"));
        Accessory sunRoof = accessoryRepository.save(new Accessory("Sunroof", "Automatic sunroof for a better driving experience"));
        Accessory gps = accessoryRepository.save(new Accessory("GPS Navigation", "Built-in GPS navigation system"));
        Accessory alloyWheels = accessoryRepository.save(new Accessory("Alloy Wheels", "Stylish alloy wheels for enhanced aesthetics"));
        return List.of(blueToothStereo, leatherSeats, sunRoof, gps, alloyWheels);
    }

    private List<Car> createAndSaveCars(List<CarModel> carModels, List<Accessory> accessories) {
        Car c1 = carRepository.save(new Car(new BigDecimal("54.89"), "2JR cTUHG8 15 Bm8502", "HRK7697", carModels.get(0), List.of(accessories.get(3), accessories.get(0)),"Manual","Gasoline",2019,"rear-wheel", 49578,"black", "https://midias.vrum.com.br/_midias/jpg/2024/01/11/honda_fit_1_4_flex_modelo_2007_cinza_de_frente_em_movimento_no_asfalto-34157751.jpg"));
        Car c2 = carRepository.save(new Car(new BigDecimal("62.89"), "4HS S6HNfw X2 Cy3255", "HDW6272", carModels.get(1), List.of(accessories.get(1), accessories.get(2), accessories.get(0)),"Automatic","Diesel",2021,"rear-wheel", 32678,"white", "https://www.kbb.com/wp-content/uploads/2019/12/01-2018-Toyota-Camry-Exterior-KBB.jpg"));
        Car c3 = carRepository.save(new Car(new BigDecimal("89.99"), "1v8 A8Me9G Xy wg1769", "IZQ1019", carModels.get(2), List.of(accessories.get(4), accessories.get(2), accessories.get(0), accessories.get(3), accessories.get(1)),"Automatic","hybrid",2023,"all-wheel", 23982,"blue", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnUnWTXtJcoFQ91h5cX3WoFlXiSkvAhG5CSg&s"));
        Car c4 = carRepository.save(new Car(new BigDecimal("49.99"), "1ns C4CmkM zE Ht5333", "IZQ1018", carModels.get(3), List.of(accessories.get(0), accessories.get(3)),"Manual","alcohol",2017,"rear-wheel", 54768,"grey", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpX-vS21F5WAWsJBORGBWvbjx-4olaGXIR9Q&s"));
        Car c5 = carRepository.save(new Car(new BigDecimal("49.99"), "1v8 A8Me9G Xy wg1762", "NEY4708", carModels.get(4), List.of(accessories.get(0), accessories.get(3), accessories.get(1)), "Manual","electric",2022,"rear-wheel", 33802,"red","https://image1.mobiauto.com.br/images/api/images/v1.0/52595736/transform/fl_progressive,f_webp,q_80"));
        return List.of(c1, c2, c3, c4, c5);
    }

    private List<Driver> createAndSaveDrivers() {
        Driver d1 = driverRepository.save(new Driver("Alice Williams", LocalDate.of(1987, 6, 18), "321.654.987-00", "alice.williams@example.com", "CNH123456789",Sex.FEMININE, AccountEmailStatusEnum.TO_CONFIRM));
        Driver d2 = driverRepository.save(new Driver("David Miller", LocalDate.of(1975, 9, 30), "654.321.987-00", "david.miller@example.com", "CNH987654321",Sex.MASCULINE, AccountEmailStatusEnum.TO_CONFIRM));
        Driver d3 = driverRepository.save(new Driver("Linda Wilson", LocalDate.of(1990, 12, 25), "123.789.456-00", "linda.wilson@example.com", "CNH192837465",Sex.FEMININE, AccountEmailStatusEnum.TO_CONFIRM));
        Driver d4 = driverRepository.save(new Driver("James Anderson", LocalDate.of(1982, 4, 14), "789.123.456-00", "james.anderson@example.com", "CNH564738291",Sex.MASCULINE, AccountEmailStatusEnum.TO_CONFIRM));
        Driver d5 = driverRepository.save(new Driver("Patricia Thomas", LocalDate.of(1995, 7, 8), "456.987.123-00", "patricia.thomas@example.com", "CNH102938475",Sex.FEMININE, AccountEmailStatusEnum.TO_CONFIRM));
        return List.of(d1, d2, d3, d4, d5);
    }

    private void createAndSaveEmployees() {
        return employeeRepository.saveAll(List.of(
                new Employee("John Doe", LocalDate.of(1985, 5, 20), "123.456.789-00", Sex.MASCULINE, "john.doe@example.com", "REG123456"),
                new Employee("Jane Smith", LocalDate.of(1990, 8, 15), "987.654.321-00", Sex.FEMININE, "jane.smith@example.com", "REG654321"),
                new Employee("Robert Johnson", LocalDate.of(1978, 3, 10), "192.837.465-00",Sex.MASCULINE, "robert.johnson@example.com", "REG789012"),
                new Employee("Emily Davis", LocalDate.of(1983, 11, 22), "564.738.291-00",Sex.FEMININE, "emily.davis@example.com", "REG210987"),
                new Employee("Michael Brown", LocalDate.of(1992, 1, 5), "102.938.475-00", Sex.MASCULINE, "michael.brown@example.com", "REG345678")
        ));
    }

    private void createAndSaveCarRentals(List<Car> cars, List<Driver> drivers, List<Employee> employees) {
        carRentalRepository.saveAll(List.of(
//                new CarRental(cars.get(0), new InsurancePolicy(true, true, BigDecimal.valueOf(2.0), true), drivers.get(0), BigDecimal.valueOf(2.0), LocalDateTime.now(), LocalDateTime.now()),
                new CarRental(cars.get(1), new InsurancePolicy(true, true, true), drivers.get(1), BigDecimal.valueOf(2.0), LocalDate.now(), LocalDate.now(), employees.get(1)),
                new CarRental(cars.get(2), new InsurancePolicy(true, true, true), drivers.get(2), BigDecimal.valueOf(2.0), LocalDate.now(), LocalDate.now(), employees.get(2)),
                new CarRental(cars.get(3), new InsurancePolicy(true, true, true), drivers.get(3), BigDecimal.valueOf(2.0), LocalDate.now(), LocalDate.now(), employees.get(3)),
                new CarRental(cars.get(4), new InsurancePolicy(true, true, true), drivers.get(4), BigDecimal.valueOf(2.0), LocalDate.now(), LocalDate.now(), employees.get(4))
        ));

    }
}
