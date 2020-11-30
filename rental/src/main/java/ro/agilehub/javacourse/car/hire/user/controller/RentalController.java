package ro.agilehub.javacourse.car.hire.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.RentApi;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RentalController implements RentApi {

    @Override
    public ResponseEntity<ResourceCreatedDTO> createReservation(@Valid NewReservationDTO newReservationDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ReservationDTO>> getAllReservations(@Valid String status) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> getReservationByID(String id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> updateReservationDetails(String id, @Valid List<PatchDocument> patchDocument) {
        return null;
    }
}
