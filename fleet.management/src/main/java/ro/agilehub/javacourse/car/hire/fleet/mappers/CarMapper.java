package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.NewCarDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;
import ro.agilehub.javacourse.car.hire.user.mappers.ObjectIdMapper;
import ro.agilehub.javacourse.car.hire.user.mappers.StatusMapper;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class, StatusMapper.class, CarClassMapper.class})
public interface CarMapper {

    @Mapping(target = "id", source = "id")
    ResourceCreatedDTO mapCarToResourceCreated(Car car);

    @Mapping(target = "status", expression = "java(ro.agilehub.javacourse.car.hire.api.model.StatusEnum.ACTIVE)")
    @Mapping(target = "id", ignore = true)
    Car mapDTOToEntity(NewCarDTO newCar);

    @Mapping(target = "id", source = "car.id")
    @Mapping(target = "make", source = "make.name")
    CarDTO mapEntityToDTO(Car car, Make make);

}
