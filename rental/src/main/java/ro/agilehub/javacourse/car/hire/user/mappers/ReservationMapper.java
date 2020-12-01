package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.NewReservationDTO;
import ro.agilehub.javacourse.car.hire.api.model.ReservationDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.user.entity.Reservation;
import ro.agilehub.javacourse.car.hire.user.entity.User;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, StatusMapper.class})
public interface ReservationMapper {

    @Mapping(target = "id", source = "id")
    ResourceCreatedDTO mapEntityToResourceCreatedDTO(Reservation reservation);

    @Mapping(target = "userId", source = "userID")
    @Mapping(target = "carId", source = "carID")
    @Mapping(target = "id", ignore = true)
    Reservation mapDTOToEntity(NewReservationDTO newReservation);


    @Mapping(target = "userID", source = "reservation.userId")
    @Mapping(target = "carID", source = "reservation.carId")
    ReservationDTO mapEntityToDTO(Reservation reservation);
}
