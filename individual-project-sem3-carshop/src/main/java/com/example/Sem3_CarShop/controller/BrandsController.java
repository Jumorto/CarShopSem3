package com.example.Sem3_CarShop.controller;


import com.example.Sem3_CarShop.business.BrandUseCases.CreateNewBrandUseCase;
import com.example.Sem3_CarShop.business.BrandUseCases.DeleteBrandUseCase;
import com.example.Sem3_CarShop.business.BrandUseCases.GetAllBrandsUseCase;
import com.example.Sem3_CarShop.business.BrandUseCases.UpdateBrandUseCase;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandRequest;
import com.example.Sem3_CarShop.domain.BrandDomain.CreateNewBrandResponse;
import com.example.Sem3_CarShop.domain.BrandDomain.GetAllBrandsResponse;
import com.example.Sem3_CarShop.domain.BrandDomain.UpdateBrandRequest;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Generated
@RestController
@RequestMapping("/brand")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class BrandsController {

    private final CreateNewBrandUseCase createNewBrandUseCase;
    private final DeleteBrandUseCase deleteBrandUseCase;
    private final GetAllBrandsUseCase getAllBrandsUseCase;

    private final UpdateBrandUseCase updateBrandUseCase;


    @GetMapping()
    public ResponseEntity<GetAllBrandsResponse> getAllBrands() {
        GetAllBrandsResponse response = getAllBrandsUseCase.getAllBrands();
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @DeleteMapping()
    public ResponseEntity<Void> deleteBrand(@RequestParam(value = "id", required = false) Long id) {
        deleteBrandUseCase.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PostMapping()
    public ResponseEntity<CreateNewBrandResponse> createNewBrand(@RequestBody @Valid CreateNewBrandRequest request) {
        CreateNewBrandResponse response = createNewBrandUseCase.createNewBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable("id") long id, @RequestBody @Valid UpdateBrandRequest request) {
        request.setId(id);
        updateBrandUseCase.updateBrand(request);
        return ResponseEntity.noContent().build();
    }
}
