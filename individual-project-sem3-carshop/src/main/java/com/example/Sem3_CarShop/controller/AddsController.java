package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.AddUseCases.*;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.AddDomain.*;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

@Generated
@RestController
@RequestMapping("/advertisement")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AddsController {

    private final CreateNewAddUseCase createNewAddUseCase;

    private final DeleteAddUseCase deleteAddUseCase;

    private final GetAllAddsUseCase getAllAddsUseCase;

    private final UpdateAdvertUseCase updateAdvertUseCase;

    private final GetAdvertByIdUseCase getAdvertByIdUseCase;

    private final UploadAdvertPhotosUseCase uploadAdvertPhotosUseCase;

    private final GetAllPhotosByAdvertID getAllPhotosByAdvertID;

    private final DeleteAllPhotosByAdvertIdUseCase deleteAllPhotosByAdvertId;



    @GetMapping()//http://localhost:8080/add?idUserCreate=1&addName=ala&description=bala&photos=1&vehicleType=1&brand=1&engine_type=1&gearbox=1&num_doors=5&date_manufacture=29-Mar-2021&kilometers=150000
    public ResponseEntity<GetAllAdvertisementsSpecialResponse> getAllAdds(@RequestParam(value = "idUserCreate", required = false) Long idUserCreate,
                                                                          @RequestParam(value = "description", required = false) String description,
                                                                          @RequestParam(value = "vehicleType", required = false) Integer vehicleType,
                                                                          @RequestParam(value = "brand", required = false) Integer brand,
                                                                          @RequestParam(value = "engine_type", required = false) Integer engine_type,
                                                                          @RequestParam(value = "gearbox", required = false) Integer gearbox,
                                                                          @RequestParam(value = "num_doors", required = false) Integer num_doors,
                                                                          @RequestParam(value = "date_manufacture", required = false) Date date_manufacture,
                                                                          @RequestParam(value = "kilometers", required = false) Long kilometers,
                                                                          @RequestParam(value = "price", required = false) Double price,
                                                                          @RequestParam(value = "date_publish", required = false) Date date_publish,
                                                                          @RequestParam(value = "priceMin", required = false) Double priceMin,
                                                                          @RequestParam(value = "priceMax", required = false) Double priceMax) throws ParseException {

        GetAllAddsRequest request = GetAllAddsRequest.builder()
                .idUserCreate(idUserCreate)
                .description(description)
                .idVehicleType(vehicleType)
                .idBrand(brand)
                .idEngineType(engine_type)
                .idGearbox(gearbox)
                .num_doors(num_doors)
                .date_manufacture(date_manufacture)
                .kilometers(kilometers)
                .price(price)
                .date_publish(date_publish)
                .priceMin(priceMin)
                .priceMax(priceMax)
                .build();
        GetAllAdvertisementsSpecialResponse response = getAllAddsUseCase.getAllAdds(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<GetAdvertByIdResponse> getAdvertById(@PathVariable("id") long id){
        GetAdvertByIdResponse response = getAdvertByIdUseCase.getAdvertById(id);
        if (response.getAdvert() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @DeleteMapping()
    public ResponseEntity<Void> deleteAdd(@RequestParam(value = "id", required = false) Long id) {
        deleteAddUseCase.deleteAdd(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @PostMapping()
    public ResponseEntity<CreateNewAddResponse> createNewAdd(@RequestBody @Valid CreateNewAddRequest request) {
        CreateNewAddResponse response = createNewAddUseCase.createNewAdd(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateAdvert(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateAdvertRequest request) {
        request.setId(id);
        updateAdvertUseCase.updateAdvert(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @PostMapping("{id}")
    public ResponseEntity<Void> uploadAdvertPhotos(@PathVariable("id") long id,@RequestBody @Valid UploadAdvertPhotosRequest request) {
        uploadAdvertPhotosUseCase.uploadPhotos(request, id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/photos/{id}")
    public ResponseEntity<GetPhotosByAdvertIDResponse> getPhotosByAdvertID(@PathVariable("id") long id){
        return ResponseEntity.ok(getAllPhotosByAdvertID.getPhotosByIdAdvert(id));
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @DeleteMapping("/photos/{id}")
    public ResponseEntity<Void> deleteAllPhotosByAdvertId(@PathVariable("id") Long id){
        deleteAllPhotosByAdvertId.deletePhotosByAdvertId(id);
        return ResponseEntity.noContent().build();
    }
}
