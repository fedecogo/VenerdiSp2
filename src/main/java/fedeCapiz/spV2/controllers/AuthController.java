package fedeCapiz.spV2.controllers;

import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.exceptions.BadRequestException;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.payloads.NewUserResponseDTO;
import fedeCapiz.spV2.payloads.UserLoginDTO;
import fedeCapiz.spV2.payloads.UserLoginResponseDTO;
import fedeCapiz.spV2.services.AuthService;
import fedeCapiz.spV2.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = usersService.save(newUserPayload);

            return new NewUserResponseDTO(newUser.getId());
        }
    }
}