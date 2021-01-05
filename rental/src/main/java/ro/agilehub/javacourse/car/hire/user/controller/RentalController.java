package ro.agilehub.javacourse.car.hire.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehb.javacourse.car.hire.api.controller.ExceptionController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.RentApi;
import ro.agilehub.javacourse.car.hire.user.entity.Reservation;
import ro.agilehub.javacourse.car.hire.user.mappers.ReservationMapper;
import ro.agilehub.javacourse.car.hire.user.service.RentalService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController extends ExceptionController implements RentApi {

    private final RentalService rentalService;
    private final ReservationMapper reservationMapper;

    @Override
    public ResponseEntity<ResourceCreatedDTO> createReservation(@Valid NewReservationDTO newReservationDTO) {
        Reservation reservation = reservationMapper.mapDTOToEntity(newReservationDTO);
        return ResponseEntity.ok(rentalService.createReservation(reservation));
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteReservation(String id) {
        return ResponseEntity.ok(rentalService.deleteReservation(id));
    }

    @Override
    public ResponseEntity<List<ReservationDTO>> getAllReservations(@Valid StatusEnum status) {
        return ResponseEntity.ok(rentalService.getAllReservations(status));
    }

    @Override
    public ResponseEntity<ReservationDTO> getReservationByID(String id) {
        return ResponseEntity.ok(rentalService.getReservation(id));
    }

    @Override
    public ResponseEntity<ResponseDTO> updateReservationDetails(String id, @Valid List<PatchDocument> patchDocument) {
        return ResponseEntity.ok(rentalService.updateReservation(id, patchDocument));
    }
}
