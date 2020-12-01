package ro.agilehub.javacourse.car.hire.user.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.user.entity.Reservation;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.mappers.ReservationMapper;
import ro.agilehub.javacourse.car.hire.user.repository.RentalRepository;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ResourceCreatedDTO createReservation(Reservation newReservation) {
        Car car = carRepository.findById(newReservation.getCarId())
                .orElseThrow(() -> new NotFoundException("Car not found"));

        User user = userRepository.findById(newReservation.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        rentalRepository.save(newReservation);

        return reservationMapper.mapEntityToResourceCreatedDTO(newReservation);
    }

    @Override
    public ResponseDTO deleteReservation(String id) {

        Reservation reservation = rentalRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        rentalRepository.delete(reservation);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("Reservation deleted successfully");
        return response;
    }

    @Override
    public List<ReservationDTO> getAllReservations(StatusEnum status) {
        List<Reservation> reservations = null;

        if (status != null) {
            reservations = rentalRepository.getAllByStatus(status.getValue());
        } else {
            reservations = rentalRepository.findAll();
        }

        return reservations.stream()
                .map(reservationMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getReservation(String id) {
        return rentalRepository.findById(new ObjectId(id))
                .map(reservationMapper::mapEntityToDTO)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
    }

    @Override
    public ResponseDTO updateReservation(String id, List<PatchDocument> patchDocuments) {

        // TODO: Patch
        return null;
    }
}
