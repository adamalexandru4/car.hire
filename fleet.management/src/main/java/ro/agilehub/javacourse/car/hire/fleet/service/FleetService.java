package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

import java.util.List;

public interface FleetService {

    CarDO addNewCar(CarDO newCar);

    void deleteCar(String id);

    CarDO getCar(String id);

    List<CarDO> getAllCarsWithStatus(StatusEnum status);

    void updateCar(CarDO car);

    void setMake(CarDO car);
}
