package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

import java.util.List;

public interface FleetService {

    ResourceCreatedDTO addNewCar(Car newCar);

    ResponseDTO deleteCar(String id);

    CarDTO getCar(String id);

    List<CarDTO> getAllCarsWithStatus(StatusEnum status);

    ResponseDTO updateCar(String id, List<PatchDocument> patchDocument);

}
