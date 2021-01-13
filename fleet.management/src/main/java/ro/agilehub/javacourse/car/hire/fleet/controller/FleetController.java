package ro.agilehub.javacourse.car.hire.fleet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehb.javacourse.car.hire.api.controller.ExceptionController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.mappers.CarMapper;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FleetController extends ExceptionController implements FleetApi {

    private final FleetService fleetService;
    private final CarMapper carMapper;

    @Override
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<ResourceCreatedDTO> addCarToFleet(@Valid NewCarDTO newCarDTO) {

        Car newCar = carMapper.mapDTOToEntity(newCarDTO);

        return ResponseEntity.ok(
                carMapper.mapCarToResourceCreated(fleetService.addNewCar(newCar))
        );
    }

    @Override
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<ResponseDTO> deleteCar(String id) {
        fleetService.deleteCar(id);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("Car deleted successfully");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<CarDTO>> getAllCarsFromFleet(@Valid StatusEnum status) {
        return ResponseEntity.ok(
                fleetService.getAllCarsWithStatus(status)
                    .stream().map(fleetService::mapCarToDTO)
                    .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<CarDTO> getCarByID(String id) {
        return ResponseEntity.ok(fleetService.mapCarToDTO(fleetService.getCar(id)));
    }

    @Override
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<ResponseDTO> updateCarDetails(String id, @Valid List<PatchDocument> patchDocument) {
        fleetService.updateCar(id, patchDocument);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("Car updated successfully");

        return ResponseEntity.ok(response);
    }
}
