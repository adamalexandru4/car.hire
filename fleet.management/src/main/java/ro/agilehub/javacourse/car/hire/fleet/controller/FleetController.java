package ro.agilehub.javacourse.car.hire.fleet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FleetController implements FleetApi {

    private final FleetService fleetService;

    @Override
    public ResponseEntity<ResourceCreatedDTO> addCarToFleet(@Valid NewCarDTO newCarDTO) {
        return ResponseEntity.ok(fleetService.addNewCar(newCarDTO));
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCar(String id) {
        return ResponseEntity.ok(fleetService.deleteCar(id));
    }

    @Override
    public ResponseEntity<List<CarDTO>> getAllCarsFromFleet(@Valid String status) {
        return ResponseEntity.ok(fleetService.getAllCarsWithStatus(status));
    }

    @Override
    public ResponseEntity<CarDTO> getCarByID(String id) {
        return ResponseEntity.ok(fleetService.getCar(id));
    }

    @Override
    public ResponseEntity<ResponseDTO> updateCarDetails(String id, @Valid List<PatchDocument> patchDocument) {
        return ResponseEntity.ok(fleetService.updateCar(id, patchDocument));
    }
}
