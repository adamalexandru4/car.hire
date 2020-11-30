package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.ReservationDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.user.entity.Reservation;

@Mapper
public interface ReservationMapper {

    @Mapping(target = "id", source = "id")
    ResourceCreatedDTO mapEntityToResourceCreatedDTO(Reservation reservation);
}
