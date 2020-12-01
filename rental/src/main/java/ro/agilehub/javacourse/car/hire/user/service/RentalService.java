package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.entity.Reservation;

import java.util.List;

public interface RentalService {

    ResourceCreatedDTO createReservation(Reservation newReservation);

    ResponseDTO deleteReservation(String id);

    List<ReservationDTO> getAllReservations(StatusEnum status);

    ReservationDTO getReservation(String id);

    ResponseDTO updateReservation(String id, List<PatchDocument> patchDocuments);

}
