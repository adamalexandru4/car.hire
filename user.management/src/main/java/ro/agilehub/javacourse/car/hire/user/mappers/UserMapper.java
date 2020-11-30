package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.agilehub.javacourse.car.hire.api.model.NewUserDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.entity.User;

@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(target = "countryOfResidence", source = "country.name"),
            @Mapping(target = "status", source = "userStatus")
    })
    UserDTO userToUserDTO(User user);

    @Mapping(target = "id", source = "id")
    ResourceCreatedDTO mapUserToResourceCreated(User user);
}
