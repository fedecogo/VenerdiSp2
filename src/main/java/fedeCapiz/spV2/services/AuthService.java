package fedeCapiz.spV2.services;


import fedeCapiz.spV2.entities.Role;
import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.exceptions.BadRequestException;
import fedeCapiz.spV2.exceptions.UnauthorizedException;
import fedeCapiz.spV2.payloads.NewUserDTO;
import fedeCapiz.spV2.payloads.UserLoginDTO;
import fedeCapiz.spV2.repositories.UserRepository;
import fedeCapiz.spV2.secutity.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;
    @Autowired
    private UserRepository usersDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        User user = usersService.findByEmail(body.email());
     if (bcrypt.matches(body.password(), user.getPassword())) {
         return jwtTools.createToken(user);
        } else {
           throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User save(NewUserDTO body) {

        usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);
        return usersDAO.save(newUser);
    }
}