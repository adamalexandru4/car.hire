package ro.agilehub.javacourse.car.hire.fleet.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehb.javacourse.car.hire.api.controller.ExceptionController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.mappers.CarMapper;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FleetController extends ExceptionController implements FleetApi {

    private final FleetService fleetService;
    private final CarMapper carMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<ResourceCreatedDTO> addCarToFleet(@Valid NewCarDTO newCarDTO) {

        CarDO newCar = carMapper.mapToDO(newCarDTO);

        return ResponseEntity.ok(carMapper.mapCarToResourceCreated(fleetService.addNewCar(newCar)));
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
                    .stream().map(carDO -> {
                        fleetService.setCarMake(carDO);
                        return carMapper.mapToDTO(carDO);
                    }).collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<CarDTO> getCarByID(String id) {

        CarDO car = fleetService.getCar(id);
        fleetService.setCarMake(car);

        return ResponseEntity.ok(carMapper.mapToDTO(car));
    }

    @Override
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<ResponseDTO> updateCarDetails(String id, @Valid List<PatchDocument> patchDocument) {
        ResponseDTO response = new ResponseDTO();

        try {
            JsonPatch jsonPatch = objectMapper.convertValue(patchDocument, JsonPatch.class);

            // Get reservation from DB
            CarDO reservation = fleetService.getCar(id);
            JsonNode reservationPatch = jsonPatch.apply(objectMapper.convertValue(reservation, JsonNode.class));
            CarDO finalCar = objectMapper.treeToValue(reservationPatch, CarDO.class);
            fleetService.updateCar(finalCar);

            response.setMessage("Reservation updated successfully");
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException | JsonPatchException e) {
            log.error(e.getMessage());

            response.setMessage("JSON patch exception");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
