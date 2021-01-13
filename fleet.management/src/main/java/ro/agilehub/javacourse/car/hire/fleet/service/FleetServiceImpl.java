package ro.agilehub.javacourse.car.hire.fleet.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;
import ro.agilehub.javacourse.car.hire.fleet.mappers.CarMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FleetServiceImpl implements FleetService {

    private final CarRepository carRepository;
    private final MakeRepository makeRepository;
    private final CarMapper carMapper;

    @Override
    public Car addNewCar(Car newCar) {
        makeRepository.findById(newCar.getMake())
                .orElseThrow(() -> new NotFoundException("Make of car not found"));
        return carRepository.save(newCar);
    }

    @Override
    public void deleteCar(String id) {

        Car car = carRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Car not found"));

        carRepository.delete(car);
    }

    @Override
    public Car getCar(String id) {
        return carRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Car not found"));
    }

    @Override
    public List<Car> getAllCarsWithStatus(StatusEnum status) {
        List<Car> cars = null;

        if (status != null) {
            cars = carRepository.getAllByStatus(status.getValue());
        } else {
            cars = carRepository.findAll();
        }

        return cars;
    }

    @Override
    public void updateCar(String id, List<PatchDocument> patchDocument) {
        // TODO: patch
    }

    public CarDTO mapCarToDTO(Car car) {
        Make make = makeRepository.findById(car.getMake())
                .orElse(null);

        return carMapper.mapEntityToDTO(car, make);
    }
}
