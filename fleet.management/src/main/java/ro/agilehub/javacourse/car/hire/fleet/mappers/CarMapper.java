package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

@Mapper
public interface CarMapper {

    @Mapping(target = "id", source = "id")
    ResourceCreatedDTO mapCarToResourceCreated(Car car);

}
