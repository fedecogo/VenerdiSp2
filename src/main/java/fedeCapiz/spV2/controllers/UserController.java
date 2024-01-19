package fedeCapiz.spV2.controllers;
import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUserDTO body){
        return  userService.save(body);
    }

    //get
    @GetMapping("")
    public Page<User> getUser(@RequestParam (defaultValue = "0") int page,@RequestParam(defaultValue = "4")int size,@RequestParam(defaultValue = "name") String sortBy){
        return userService.getUser(page, size, sortBy);
    }

}
