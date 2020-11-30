package ro.agilehub.javacourse.car.hire.fleet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.NewCarDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;
import ro.agilehub.javacourse.car.hire.fleet.enums.CarStatusEnum;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;

@Service
@RequiredArgsConstructor
public class FleetService {

    private final CarRepository carRepository;
    private final MakeRepository makeRepository;

    private ResourceCreatedDTO addCar(NewCarDTO newCarDTO) {

        Make make = makeRepository.findByName(newCarDTO.getMake())
            .orElseThrow(() -> new NotFoundException("Make of the car not found"));

        return new ResourceCreatedDTO();

//        Car car = Car.builder()
//                .year(newCarDTO.getYear())
//                .fuel(newCarDTO.getFuel())
//                .make(make)
//                .mileage(newCarDTO.getMileage())
//                .model(newCarDTO.getModel())
//                .carClass(newCarDTO.getCarClass())
//                .status(CarStatusEnum.ACTIVE)
//                .build();

    }
}
