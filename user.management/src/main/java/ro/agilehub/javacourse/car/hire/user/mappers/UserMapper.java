package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.NewUserDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.entity.User;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface UserMapper {

    @Mapping(target = "countryID", source = "countryOfResidence")
    UserDO mapToDO(UserDTO userDTO);

    @Mapping(target = "status", expression = "java(ro.agilehub.javacourse.car.hire.api.model.StatusEnum.ACTIVE)")
    @Mapping(target = "countryID", source = "countryOfResidence")
    UserDO mapToDO(NewUserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "countryID")
    User mapToEntity(UserDO userDO);

    @Mapping(target = "countryID", source = "country")
    UserDO mapToDO(User user);

    @Mapping(target = "countryID", source = "country")
    List<UserDO> mapToDOList(List<User> users);

    @Mapping(target = "countryOfResidence", source = "countryDO.name")
    UserDTO mapToDTO(UserDO userDTO);

    ResourceCreatedDTO mapUserToResourceCreated(UserDO user);
}
