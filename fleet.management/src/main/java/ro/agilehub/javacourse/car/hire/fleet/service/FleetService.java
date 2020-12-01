package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.*;

import java.util.List;

public interface FleetService {

    ResourceCreatedDTO addNewCar(NewCarDTO newCar);

    ResponseDTO deleteCar(String id);

    CarDTO getCar(String id);

    List<CarDTO> getAllCarsWithStatus(String status);

    ResponseDTO updateCar(String id, List<PatchDocument> patchDocument);

}
