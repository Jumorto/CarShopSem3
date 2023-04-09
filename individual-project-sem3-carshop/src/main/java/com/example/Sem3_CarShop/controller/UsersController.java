package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.business.UserUseCases.*;
import com.example.Sem3_CarShop.configuration.security.isauthenticated.IsAuthenticated;
import com.example.Sem3_CarShop.domain.UserDomain.*;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Base64;
import java.util.Optional;

@Generated
@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UsersController {

    private final CreateUserUseCase createUserUseCase;

    private final GetUserUseCase getUserUseCase;

    private final GetUsersByTypeUseCase getUsersByTypeUseCase;

    private final DeleteUserUseCase deleteUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final GetUserForAdvertUseCase getUserForAdvertUseCase;


    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @GetMapping("{id}")//http://localhost:8080/users/5
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    //@GetMapping()//http://localhost:8080/users?type=1
    @GetMapping()//http://localhost:8080/users?type=1&name=Pancho
    public ResponseEntity<GetAllUsersResponse> getUserByType(@RequestParam(value = "type", required = false) Integer type) {
        GetUsersByTypeRequest request = GetUsersByTypeRequest.builder().type(type).build();
        GetAllUsersResponse response = getUsersByTypeUseCase.getUsersByType(request);
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //@CrossOrigin("http://localhost:3000")
    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin", "ROLE_normal"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        System.out.println("ResponseEntity - original photoString ");
        if(request.getPhotoStr() != null && !request.getPhotoStr().isEmpty()) {
            String str[] = request.getPhotoStr().split(","); // I remove the beginning of the string "data:image/jpeg;base64,"
            byte[] decodedBytes = Base64.getDecoder().decode(str[1]);
            request.setPhoto(decodedBytes);
        }
        request.setId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping({"userForAdvert/{idUser}"})
    public ResponseEntity<CustomUserForAdvert> getUserForAdvert(@PathVariable(value = "idUser") final long idUser){
        return ResponseEntity.ok().body(getUserForAdvertUseCase.getUserForAdvertById(idUser));
    }
}
