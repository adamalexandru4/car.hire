package ro.agilehub.javacourse.car.hire.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.NewReservationDTO;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.user.repository.RentalRepository;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

}
