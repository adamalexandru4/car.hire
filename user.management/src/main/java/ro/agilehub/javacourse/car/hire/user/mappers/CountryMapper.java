package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.user.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;

@Mapper(config = BaseMapperConfig.class)
public interface CountryMapper {

    CountryDO mapToDO(Country country);

    Country mapToEntity(CountryDO countryDO);
}
