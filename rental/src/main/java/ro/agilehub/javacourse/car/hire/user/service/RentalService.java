package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.api.model.*;

import java.util.List;

public interface RentalService {

    ResourceCreatedDTO createReservation(NewReservationDTO newReservation);

    ResponseDTO deleteReservation(String id);

    List<ReservationDTO> getAllReservations(String status);

    ReservationDTO getReservation(String id);

    ResponseDTO updateReservation(String id, List<PatchDocument> patchDocuments);

}
