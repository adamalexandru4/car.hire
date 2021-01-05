package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.NewUserDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, StatusMapper.class })
public interface UserMapper {

    @Mapping(target = "countryOfResidence", source = "country.name")
    @Mapping(target = "status", source = "user.userStatus")
    @Mapping(target = "id", source = "user.id")
    UserDTO userToUserDTO(User user, Country country);

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "userStatus", expression = "java(ro.agilehub.javacourse.car.hire.api.model.StatusEnum.ACTIVE)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "countryOfResidence")
    User mapDTOToEntity(NewUserDTO userDTO);

    ResourceCreatedDTO mapUserToResourceCreated(User user);
}
