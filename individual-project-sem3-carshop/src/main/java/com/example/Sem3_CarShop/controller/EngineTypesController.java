package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.EngineTypeUseCases.CreateNewEngineTypeUseCase;
import com.example.Sem3_CarShop.business.EngineTypeUseCases.DeleteEngineTypeUseCase;
import com.example.Sem3_CarShop.business.EngineTypeUseCases.GetAllEngineTypesUseCase;
import com.example.Sem3_CarShop.business.EngineTypeUseCases.UpdateEngineTypeUseCase;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeRequest;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeResponse;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.GetAllEngineTypesResponse;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.UpdateEngineTypeRequest;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Generated
@RestController
@RequestMapping("/engineType")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class EngineTypesController {
    private final CreateNewEngineTypeUseCase createNewEngineTypeUseCase;
    private final DeleteEngineTypeUseCase deleteEngineTypeUseCase;
    private final GetAllEngineTypesUseCase getAllEngineTypesUseCase;

    private final UpdateEngineTypeUseCase updateEngineTypeUseCase;


    @GetMapping()
    public ResponseEntity<GetAllEngineTypesResponse> getAllEngineTypes() {
        GetAllEngineTypesResponse response = getAllEngineTypesUseCase.getAllEngineTypes();
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @DeleteMapping()
    public ResponseEntity<Void> deleteEngineType(@RequestParam(value = "id", required = false) Long id) {
        deleteEngineTypeUseCase.deleteEngineType(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PostMapping()
    public ResponseEntity<CreateNewEngineTypeResponse> createNewEngineType(@RequestBody @Valid CreateNewEngineTypeRequest request) {
        CreateNewEngineTypeResponse response = createNewEngineTypeUseCase.createNewEngineType(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateEngineType(@PathVariable("id") long id, @RequestBody @Valid UpdateEngineTypeRequest request) {
        request.setId(id);
        updateEngineTypeUseCase.updateEngineType(request);
        return ResponseEntity.noContent().build();
    }
}
