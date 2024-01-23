package fedeCapiz.spV2.services;

import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.exceptions.NotFoundException;
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
    public User findById(int id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Page<User> getUser(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
       return userRepository.findAll(pageable);
    }



    public User findByIdAndUpdate(int id, User body) {
        User found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        return userRepository.save(found);
    }

    public void findByIdAndDelete(int id) {
        User found = this.findById(id);
        userRepository.delete(found);
    }
    public User findByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }
}
