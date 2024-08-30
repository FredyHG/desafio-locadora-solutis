package com.squad7.desafiolocadorasolutis.config;

import com.squad7.desafiolocadorasolutis.enums.Category;
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
    public void run(String... args) throws Exception {

        if (manufacturerRepository.findAll().isEmpty()) {
            Manufacturer manufacturer1 = manufacturerRepository.save(
                    new Manufacturer("Manufacturer X")
            );

            Manufacturer manufacturer2 = manufacturerRepository.save(
                    new Manufacturer("Manufacturer Y")
            );

            Manufacturer manufacturer3 = manufacturerRepository.save(
                    new Manufacturer("Manufacturer Z")
            );


            CarModel compactHatchX = carModelRepository.save(
                    new CarModel("Compact Hatchback X",
                            Category.COMPACT_HATCH,
                            manufacturer1)
            );

            CarModel mediumSedanY = carModelRepository.save(
                    new CarModel("Medium Sedan Y",
                            Category.MEDIUM_SEDAN, manufacturer2)
            );

            CarModel sportyZ = carModelRepository.save(
                    new CarModel("Sporty Z",
                            Category.SPORT, manufacturer3)
            );

            CarModel minivanV = carModelRepository.save(
                    new CarModel("Minivan V",
                            Category.MINIVAN, manufacturer2)
            );

            CarModel compactSedanW = carModelRepository.save(
                    new CarModel("Compact Sedan W",
                            Category.COMPACT_SEDAN, manufacturer1)
            );

            Accessory blueToothStereo = accessoryRepository.save(
                    new Accessory("Bluetooth Stereo",
                            "Bluetooth-enabled stereo system")
            );

            Accessory letherSeats = accessoryRepository.save(
                    new Accessory("Leather Seats",
                            "High-quality leather seats for comfort")
            );

            Accessory sunRoof = accessoryRepository.save(
                    new Accessory("Sunroof",
                            "Automatic sunroof for a better driving experience")
            );

            Accessory gps = accessoryRepository.save(
                    new Accessory("GPS Navigation",
                            "Built-in GPS navigation system")
            );

            Accessory alloyWheels = accessoryRepository.save(
                    new Accessory("Alloy Wheels",
                            "Stylish alloy wheels for enhanced aesthetics")
            );

            Car c1 = carRepository.save(
                    new Car(new BigDecimal("54.89"),
                            "2JR cTUHG8 15 Bm8502",
                            "HRK7697",
                            compactHatchX,
                            List.of(gps, blueToothStereo),
                            "https://midias.vrum.com.br/_midias/jpg/2024/01/11/honda_fit_1_4_flex_modelo_2007_cinza_de_frente_em_movimento_no_asfalto-34157751.jpg"
                    )
            );

            Car c2 = carRepository.save(
                    new Car(new BigDecimal("62.89"),
                            "4HS S6HNfw X2 Cy3255",
                            "HDW6272",
                            mediumSedanY,
                            List.of(letherSeats, sunRoof, blueToothStereo),
                            "https://www.kbb.com/wp-content/uploads/2019/12/01-2018-Toyota-Camry-Exterior-KBB.jpg"
                    )
            );

            Car c3 = carRepository.save(
                    new Car(new BigDecimal("89.99"),
                            "1v8 A8Me9G Xy wg1769",
                            "IZQ1019",
                            sportyZ,
                            List.of(alloyWheels, sunRoof, blueToothStereo, gps, letherSeats),
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnUnWTXtJcoFQ91h5cX3WoFlXiSkvAhG5CSg&s"
                    )
            );

            Car c4 = carRepository.save(
                    new Car(new BigDecimal("49.99"),
                            "1ns C4CmkM zE Ht5333",
                            "IZQ1018",
                            minivanV,
                            List.of(blueToothStereo, gps),
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpX-vS21F5WAWsJBORGBWvbjx-4olaGXIR9Q&s"
                    )
            );

            Car c5 = carRepository.save(
                    new Car(new BigDecimal("49.99"),
                            "1v8 A8Me9G Xy wg1762",
                            "NEY4708",
                            compactSedanW,
                            List.of(blueToothStereo, gps, letherSeats),
                            "https://image1.mobiauto.com.br/images/api/images/v1.0/52595736/transform/fl_progressive,f_webp,q_80"
                    )
            );

            Driver d1 = driverRepository.save(
                    new Driver(
                            "Alice Williams",
                            LocalDate.of(1987, 6, 18),
                            "321.654.987-00",
                            "alice.williams@example.com",
                            "CNH123456789"
                    )
            );

            Driver d2 = driverRepository.save(
                    new Driver(
                            "David Miller",
                            LocalDate.of(1975, 9, 30),
                            "654.321.987-00",
                            "david.miller@example.com",
                            "CNH987654321"
                    )
            );

            Driver d3 = driverRepository.save(
                    new Driver(
                            "Linda Wilson",
                            LocalDate.of(1990, 12, 25),
                            "123.789.456-00",
                            "linda.wilson@example.com",
                            "CNH192837465"
                    )
            );

            Driver d4 = driverRepository.save(
                    new Driver(
                            "James Anderson",
                            LocalDate.of(1982, 4, 14),
                            "789.123.456-00",
                            "james.anderson@example.com",
                            "CNH564738291"
                    )
            );

            Driver d5 = driverRepository.save(
                    new Driver(
                            "Patricia Thomas",
                            LocalDate.of(1995, 7, 8),
                            "456.987.123-00",
                            "patricia.thomas@example.com",
                            "CNH102938475"
                    )
            );


            employeeRepository.save(
                    new Employee(
                            "John Doe",
                            LocalDate.of(1985, 5, 20),
                            "123.456.789-00",
                            "john.doe@example.com",
                            "REG123456"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Jane Smith",
                            LocalDate.of(1990, 8, 15),
                            "987.654.321-00",
                            "jane.smith@example.com",
                            "REG654321"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Robert Johnson",
                            LocalDate.of(1978, 3, 10),
                            "192.837.465-00",
                            "robert.johnson@example.com",
                            "REG789012"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Emily Davis",
                            LocalDate.of(1983, 11, 22),
                            "564.738.291-00",
                            "emily.davis@example.com",
                            "REG210987"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Michael Brown",
                            LocalDate.of(1992, 1, 5),
                            "102.938.475-00",
                            "michael.brown@example.com",
                            "REG345678"
                    )
            );

            InsurancePolicy insurancePolicy = new InsurancePolicy(true, true, BigDecimal.valueOf(2.0), true);

            InsurancePolicy insurancePolicy2 = new InsurancePolicy(true, true, BigDecimal.valueOf(2.0), true);

            InsurancePolicy insurancePolicy3 = new InsurancePolicy(true, true, BigDecimal.valueOf(2.0), true);

            InsurancePolicy insurancePolicy4 = new InsurancePolicy(true, true, BigDecimal.valueOf(2.0), true);

            InsurancePolicy insurancePolicy5 = new InsurancePolicy(true, true, BigDecimal.valueOf(2.0), true);

            CarRental cr1 = new CarRental(c1, insurancePolicy, d1, BigDecimal.valueOf(2.0), LocalDateTime.now(), LocalDateTime.now());

            CarRental cr2 = new CarRental(c2, insurancePolicy2, d2, BigDecimal.valueOf(2.0), LocalDateTime.now(), LocalDateTime.now());

            CarRental cr3 = new CarRental(c3, insurancePolicy3, d3, BigDecimal.valueOf(2.0), LocalDateTime.now(), LocalDateTime.now());

            CarRental cr4 = new CarRental(c4, insurancePolicy4, d4, BigDecimal.valueOf(2.0), LocalDateTime.now(), LocalDateTime.now());

            CarRental cr5 = new CarRental(c5, insurancePolicy5, d5, BigDecimal.valueOf(2.0), LocalDateTime.now(), LocalDateTime.now());

            carRentalRepository.saveAll(List.of(cr1, cr2, cr3, cr4, cr5));
        }
    }
}
