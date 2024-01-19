package fedeCapiz.spV2.controllers;
import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUserDTO body){
        return  userService.save(body);
    }

}
