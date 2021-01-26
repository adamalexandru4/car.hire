package ro.agilehub.javacourse.car.hire.fleet.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.mappers.CarMapper;
import ro.agilehub.javacourse.car.hire.fleet.mappers.MakeMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FleetServiceImpl implements FleetService {

    private final CarRepository carRepository;
    private final MakeRepository makeRepository;
    private final CarMapper carMapper;
    private final MakeMapper makeMapper;

    @Override
    public CarDO addNewCar(CarDO newCar) {
        setCarMake(newCar);
        var carCreated =  carRepository.save(carMapper.mapToEntity(newCar));
        return carMapper.mapToDO(carCreated);
    }

    @Override
    public void deleteCar(String id) {
        ObjectId carID = new ObjectId(id);

        if (carRepository.existsById(carID)) {
            carRepository.deleteById(carID);
        }
    }

    @Override
    public CarDO getCar(String id) {
        return carMapper.mapToDO(carRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Car not found")));
    }

    @Override
    public List<CarDO> getAllCarsWithStatus(StatusEnum status) {
        List<CarDO> cars = null;

        if (status != null) {
            cars = carMapper.mapToDOList(carRepository.getAllByStatus(status.getValue()));
        } else {
            cars = carMapper.mapToDOList(carRepository.findAll());
        }

        return cars;
    }

    @Override
    public void updateCar(CarDO car) {
        var carUpdated = carMapper.mapToEntity(car);
        carRepository.save(carUpdated);
    }

    public void setCarMake(CarDO car) {
        MakeDO make = makeMapper.mapToDO(makeRepository.findById(new ObjectId(car.getMakeID()))
                .orElseThrow(() -> new NotFoundException("Make of car not found")));
        car.setMakeDO(make);
    }
}
