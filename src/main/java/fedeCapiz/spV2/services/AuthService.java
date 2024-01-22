package fedeCapiz.spV2.services;


import fedeCapiz.spV2.entities.User;
import fedeCapiz.spV2.exceptions.UnauthorizedException;
import fedeCapiz.spV2.payloads.UserLoginDTO;
import fedeCapiz.spV2.secutity.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        User user = usersService.findByEmail(body.email());
        if (body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}