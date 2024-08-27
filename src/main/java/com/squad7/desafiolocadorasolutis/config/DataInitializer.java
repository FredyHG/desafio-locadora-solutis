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
import java.util.List;
import java.util.UUID;

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

    @Override
    public void run(String... args) throws Exception {

        UUID id = UUID.fromString("3f4d52d0-23e1-4f35-bb9d-67f08c8b3652");

        if (carRepository.findById(id).isEmpty()){
            Manufacturer manufacturer1 = manufacturerRepository.save(
                    new Manufacturer(UUID.fromString
                            ("3f4d52d0-23e1-4f35-bb9d-67f08c8b3652"),
                            "Manufacturer X")
            );

            Manufacturer manufacturer2 = manufacturerRepository.save(
                    new Manufacturer(UUID.fromString(
                            "a8a58c88-4f1c-4b22-82a4-5d6c8b0de8f3"),
                            "Manufacturer Y")
            );

            Manufacturer manufacturer3 = manufacturerRepository.save(
                    new Manufacturer(UUID.fromString(
                            "2ac138cc-555e-4059-8e78-5d6ab4ca52ae"),
                            "Manufacturer Z")
            );


            CarModel compactHatchX = carModelRepository.save(
                    new CarModel(UUID.fromString("d5c1a7ef-7c34-46ec-8e3e-4f6e3b6449b1"),
                            "Compact Hatchback X",
                            Category.COMPACT_HATCH,
                            manufacturer1)
            );

            CarModel mediumSedanY = carModelRepository.save(
                    new CarModel(UUID.fromString("1e832a70-4d43-4e9d-bd27-8fce823b1d6d"),
                            "Medium Sedan Y",
                            Category.MEDIUM_SEDAN, manufacturer2)
            );

            CarModel sportyZ =carModelRepository.save(
                    new CarModel(UUID.fromString("8d1cb623-b54b-4acb-92e8-d56b891d8754")
                            , "Sporty Z",
                            Category.SPORT, manufacturer3)
            );

            CarModel minivanV = carModelRepository.save(
                    new CarModel(UUID.fromString("e3b7c485-d7e4-4c5b-9b79-f476bd013f46"),
                            "Minivan V",
                            Category.MINIVAN, manufacturer2)
            );

            CarModel compactSedanW = carModelRepository.save(
                    new CarModel(UUID.fromString("b8fbc7a3-ff8d-4a2b-9829-88d393f1e8d7"),
                            "Compact Sedan W",
                            Category.COMPACT_SEDAN, manufacturer1)
            );

            Accessory blueToothStereo = accessoryRepository.save(
                    new Accessory(UUID.fromString("b8fbc7a3-ff8d-4a2b-9829-88d393f1e8d7"),
                            "Bluetooth Stereo",
                            "Bluetooth-enabled stereo system")
            );

            Accessory letherSeats = accessoryRepository.save(
                    new Accessory(UUID.fromString("b1c5d88e-2e30-4a9d-b90e-3b60e849dfb4"),
                            "Leather Seats",
                            "High-quality leather seats for comfort")
            );

            Accessory sunRoof = accessoryRepository.save(
                    new Accessory(UUID.fromString("d2f8c5f8-4d76-47f5-86a6-93a2b9b7b5a7"),
                            "Sunroof",
                            "Automatic sunroof for a better driving experience")
            );

            Accessory gps = accessoryRepository.save(
                    new Accessory(UUID.fromString("e5a9e1c6-62f8-4b2e-89d2-84c5b032d7f8"),
                            "GPS Navigation",
                            "Built-in GPS navigation system")
            );

            Accessory alloyWheels = accessoryRepository.save(
                    new Accessory(UUID.fromString("f0c76e56-70c5-4a44-b60b-d1d8dceec5d1"),
                            "Alloy Wheels",
                            "Stylish alloy wheels for enhanced aesthetics")
            );

            carRepository.save(
                    new Car(UUID.fromString("f1d6ab9c-3ab4-4c46-86c1-2bb5350d1913"),
                            new BigDecimal("54.89"),
                            "2JR cTUHG8 15 Bm8501",
                            "HRK7697",
                            compactHatchX,
                            List.of(gps, blueToothStereo))
            );

            carRepository.save(
                    new Car(UUID.fromString("a1d3c556-9dae-48b9-846d-d7e0cb34695e"),
                            new BigDecimal("62.89"),
                            "4HS S6HNfw X2 Cy3255",
                            "HDW6272",
                            mediumSedanY,
                            List.of(letherSeats, sunRoof, blueToothStereo))
            );

            carRepository.save(
                    new Car(UUID.fromString("b6a060f0-9959-4c2e-963e-4c461c51f380"),
                            new BigDecimal("89.99"),
                            "1v8 A8Me9G Xy wg1769",
                            "IZQ1019",
                            sportyZ,
                            List.of(alloyWheels, sunRoof, blueToothStereo, gps, letherSeats))
            );

            carRepository.save(
                    new Car(UUID.fromString("651f867b-e3d3-4090-ab7c-d8f10f2dce53"),
                            new BigDecimal("49.99"),
                            "1ns C4CmkM zE Ht5333",
                            "IZQ1018",
                            minivanV,
                            List.of( blueToothStereo, gps))
            );

            carRepository.save(
                    new Car(UUID.fromString("651f867b-e3d3-4090-ab7c-d8f10f2dce53"),
                            new BigDecimal("49.99"),
                            "1v8 A8Me9G Xy wg1762",
                            "NEY4708",
                            compactSedanW,
                            List.of( blueToothStereo, gps, letherSeats))
            );

            driverRepository.save(
                    new Driver(
                            "Alice Williams",
                            LocalDate.of(1987, 6, 18),
                            "321.654.987-00",
                            "alice.williams@example.com",
                            UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                            "CNH123456789"
                    )
            );

            driverRepository.save(
                    new Driver(
                            "David Miller",
                            LocalDate.of(1975, 9, 30),
                            "654.321.987-00",
                            "david.miller@example.com",
                            UUID.fromString("223e4567-e89b-12d3-a456-426614174001"),
                            "CNH987654321"
                    )
            );

            driverRepository.save(
                    new Driver(
                            "Linda Wilson",
                            LocalDate.of(1990, 12, 25),
                            "123.789.456-00",
                            "linda.wilson@example.com",
                            UUID.fromString("323e4567-e89b-12d3-a456-426614174002"),
                            "CNH192837465"
                    )
            );

            driverRepository.save(
                    new Driver(
                            "James Anderson",
                            LocalDate.of(1982, 4, 14),
                            "789.123.456-00",
                            "james.anderson@example.com",
                            UUID.fromString("423e4567-e89b-12d3-a456-426614174003"),
                            "CNH564738291"
                    )
            );

            driverRepository.save(
                    new Driver(
                            "Patricia Thomas",
                            LocalDate.of(1995, 7, 8),
                            "456.987.123-00",
                            "patricia.thomas@example.com",
                            UUID.fromString("523e4567-e89b-12d3-a456-426614174004"),
                            "CNH102938475"
                    )
            );


            employeeRepository.save(
                    new Employee(
                            "John Doe",
                            LocalDate.of(1985, 5, 20),
                            "123.456.789-00",
                            "john.doe@example.com",
                            UUID.fromString("a1e4567b-e89b-12d3-a456-426614174000"),
                            "REG123456"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Jane Smith",
                            LocalDate.of(1990, 8, 15),
                            "987.654.321-00",
                            "jane.smith@example.com",
                            UUID.fromString("b2e4567b-e89b-12d3-a456-426614174001"),
                            "REG654321"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Robert Johnson",
                            LocalDate.of(1978, 3, 10),
                            "192.837.465-00",
                            "robert.johnson@example.com",
                            UUID.fromString("c3e4567b-e89b-12d3-a456-426614174002"),
                            "REG789012"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Emily Davis",
                            LocalDate.of(1983, 11, 22),
                            "564.738.291-00",
                            "emily.davis@example.com",
                            UUID.fromString("d4e4567b-e89b-12d3-a456-426614174003"),
                            "REG210987"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            "Michael Brown",
                            LocalDate.of(1992, 1, 5),
                            "102.938.475-00",
                            "michael.brown@example.com",
                            UUID.fromString("e5e4567b-e89b-12d3-a456-426614174004"),
                            "REG345678"
                    )
            );
        }
    }
}
