package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.VehicleTypeUseCases.CreateNewVehicleTypeUseCase;
import com.example.Sem3_CarShop.business.VehicleTypeUseCases.DeleteVehicleTypeUseCase;
import com.example.Sem3_CarShop.business.VehicleTypeUseCases.GetAllVehicleTypesUseCase;
import com.example.Sem3_CarShop.business.VehicleTypeUseCases.UpdateVehicleTypeUseCase;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeRequest;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeResponse;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.GetAllVehicleTypesResponse;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.UpdateVehicleTypeRequest;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Generated
@RestController
@RequestMapping("/vehicleType")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class VehicleTypesController {
    private final CreateNewVehicleTypeUseCase createNewVehicleTypeUseCase;
    private final DeleteVehicleTypeUseCase deleteVehicleTypeUseCase;
    private final GetAllVehicleTypesUseCase getAllVehicleTypesUseCase;
    private final UpdateVehicleTypeUseCase updateVehicleTypeUseCase;


    @GetMapping()
    public ResponseEntity<GetAllVehicleTypesResponse> getAllVehicleTypes() {
        GetAllVehicleTypesResponse response = getAllVehicleTypesUseCase.getAllVehicleTypes();
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @DeleteMapping()
    public ResponseEntity<Void> deleteVehicleType(@RequestParam(value = "id", required = false) Long id) {
        deleteVehicleTypeUseCase.deleteVehicleType(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PostMapping()
    public ResponseEntity<CreateNewVehicleTypeResponse> createNewVehicleType(@RequestBody @Valid CreateNewVehicleTypeRequest request) {
        CreateNewVehicleTypeResponse response = createNewVehicleTypeUseCase.createNewVehicleType(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateVehicleType(@PathVariable("id") long id, @RequestBody @Valid UpdateVehicleTypeRequest request) {
        request.setId(id);
        updateVehicleTypeUseCase.updateVehicleType(request);
        return ResponseEntity.noContent().build();
    }
}
