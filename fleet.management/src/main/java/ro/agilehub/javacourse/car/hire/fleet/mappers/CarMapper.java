package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.NewCarDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface CarMapper {

    ResourceCreatedDTO mapCarToResourceCreated(CarDO car);

    @Mapping(target = "status", expression = "java(ro.agilehub.javacourse.car.hire.api.model.StatusEnum.ACTIVE)")
    @Mapping(target = "makeID", source = "make")
    CarDO mapToDO(NewCarDTO newCar);

    @Mapping(target = "makeID", source = "make")
    CarDO mapToDO(Car newCar);

    @Mapping(target = "makeID", source = "make")
    List<CarDO> mapToDOList(List<Car> newCar);

    @Mapping(target = "make", source = "makeDO.name")
    CarDTO mapToDTO(CarDO car);

    @Mapping(target = "make", source = "makeID")
    Car mapToEntity(CarDO car);
}
