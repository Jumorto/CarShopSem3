package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.GearboxUseCases.CreateNewGearboxUseCase;
import com.example.Sem3_CarShop.business.GearboxUseCases.DeleteGearboxUseCase;
import com.example.Sem3_CarShop.business.GearboxUseCases.GetAllGearboxesUseCase;
import com.example.Sem3_CarShop.business.GearboxUseCases.UpdateGearboxUseCase;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxRequest;
import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxResponse;
import com.example.Sem3_CarShop.domain.GearboxDomain.GetAllGearboxesResponse;
import com.example.Sem3_CarShop.domain.GearboxDomain.UpdateGearboxRequest;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Generated
@RestController
@RequestMapping("/gearbox")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class GearboxController {
    private final CreateNewGearboxUseCase createNewGearboxUseCase;
    private final DeleteGearboxUseCase deleteGearboxUseCase;
    private final GetAllGearboxesUseCase getAllGearboxesUseCase;
    private final UpdateGearboxUseCase updateGearboxUseCase;


    @GetMapping()
    public ResponseEntity<GetAllGearboxesResponse> getAllGearboxes() {
        GetAllGearboxesResponse response = getAllGearboxesUseCase.getAllGearboxes();
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @DeleteMapping()
    public ResponseEntity<Void> deleteGearbox(@RequestParam(value = "id", required = false) Long id) {
        deleteGearboxUseCase.deleteGearbox(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PostMapping()
    public ResponseEntity<CreateNewGearboxResponse> createNewGearbox(@RequestBody @Valid CreateNewGearboxRequest request) {
        CreateNewGearboxResponse response = createNewGearboxUseCase.createNewGearbox(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateGearbox(@PathVariable("id") long id, @RequestBody @Valid UpdateGearboxRequest request) {
        request.setId(id);
        updateGearboxUseCase.updateGearbox(request);
        return ResponseEntity.noContent().build();
    }
}
