package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;
import ro.agilehub.javacourse.car.hire.user.mappers.ObjectIdMapper;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface MakeMapper {

    MakeDO mapToDO(Make make);

    Make mapToEntity(MakeDO makeDO);
}
