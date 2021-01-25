package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;

@MapperConfig(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        uses = { ObjectIdMapper.class, StatusMapper.class })
public interface BaseMapperConfig {
}
