package fedeCapiz.spV2.services;

import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<User> getUser(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userRepository.findAll(pageable);
    }

}
