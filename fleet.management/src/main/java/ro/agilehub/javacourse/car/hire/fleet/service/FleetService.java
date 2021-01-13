package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

import java.util.List;

public interface FleetService {

    Car addNewCar(Car newCar);

    void deleteCar(String id);

    Car getCar(String id);

    List<Car> getAllCarsWithStatus(StatusEnum status);

    void updateCar(String id, List<PatchDocument> patchDocument);

    CarDTO mapCarToDTO(Car car);
}
