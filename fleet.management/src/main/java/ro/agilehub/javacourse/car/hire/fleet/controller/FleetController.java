package ro.agilehub.javacourse.car.hire.fleet.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FleetController implements FleetApi {

    @Override
    public ResponseEntity<ResourceCreatedDTO> addCarToFleet(@Valid NewCarDTO newCarDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCar(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<CarDTO>> getAllCarsFromFleet(@Valid String status) {
        return null;
    }

    @Override
    public ResponseEntity<CarDTO> getCarByID(String id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> updateCarDetails(String id, @Valid List<PatchDocument> patchDocument) {
        return null;
    }
}
