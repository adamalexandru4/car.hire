package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ro.agilehub.javacourse.car.hire.user.mappers.ObjectIdMapper;
import ro.agilehub.javacourse.car.hire.user.mappers.StatusMapper;

@MapperConfig(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        uses = { ObjectIdMapper.class, StatusMapper.class, CarClassMapper.class })
public interface BaseMapperConfig {
}
