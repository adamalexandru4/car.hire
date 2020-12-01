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
    public ResourceCreatedDTO addNewCar(Car newCar) {
        makeRepository.findById(newCar.getMake())
                .orElseThrow(() -> new NotFoundException("Make of car not found"));
        carRepository.save(newCar);

        return carMapper.mapCarToResourceCreated(newCar);
    }

    @Override
    public ResponseDTO deleteCar(String id) {

        Car car = carRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Car not found"));

        carRepository.delete(car);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("Car deleted successfully");
        return response;
    }

    @Override
    public CarDTO getCar(String id) {
        return carRepository.findById(new ObjectId(id))
                .map(this::mapCarToDTO)
                .orElseThrow(() -> new NotFoundException("Car not found"));
    }

    @Override
    public List<CarDTO> getAllCarsWithStatus(StatusEnum status) {
        List<Car> cars = null;

        if (status != null) {
            cars = carRepository.getAllByStatus(status.getValue());
        } else {
            cars = carRepository.findAll();
        }

        return cars.stream()
                .map(this::mapCarToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO updateCar(String id, List<PatchDocument> patchDocument) {
        // TODO: patch

        ResponseDTO response = new ResponseDTO();
        response.setMessage("Car updated successfully");
        return response;
    }

    private CarDTO mapCarToDTO(Car car) {
        Make make = makeRepository.findById(car.getMake())
                .orElse(null);

        return carMapper.mapEntityToDTO(car, make);
    }
}
