package fedeCapiz.spV2.controllers;
import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    //findById
    @Autowired
    UserService userService;


/*

//vecchio crud

    //get vecchia
    @GetMapping("")
    public Page<User> getUser(@RequestParam (defaultValue = "0") int page,@RequestParam(defaultValue = "4")int size,@RequestParam(defaultValue = "name") String sortBy){
        return userService.getUser(page, size, sortBy);
    }


    //put vecchia
    @PutMapping("/{userId}")
    public User findAndUpdate(@PathVariable int userId, @RequestBody User body) {
        return userService.findByIdAndUpdate(userId, body);
    }

      //delate
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int userId) {
        userService.findByIdAndDelete(userId);
    }
        //post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUserDTO body){
        return  userService.save(body);
    }


    */

    //get /me

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    // get solo per gli admin, perche Solo gli admin possono leggere l'elenco degli utenti
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUser(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy) {
        return userService.getUser(page, size, orderBy);
    }
    // get by userId   ma è solo per admin??
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.findById(userId);
    }







}
