package fedeCapiz.spV2.services;

import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(NewUserDTO body){
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.userName());
       return userRepository.save(newUser);
    }

}
