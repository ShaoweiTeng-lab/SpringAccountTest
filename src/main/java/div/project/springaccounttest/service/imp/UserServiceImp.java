package div.project.springaccounttest.service.imp;


import div.project.springaccounttest.dao.UserRepository;
import div.project.springaccounttest.dto.SignUpRequest;
import div.project.springaccounttest.service.UserService;
import div.project.springaccounttest.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImp  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Override
    public String signUp(SignUpRequest signUpRequest) {
        User  user  = userRepository.findByUserAccount(signUpRequest.getUserAccount());
        if (user!=null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"已有重複帳號");
        user =new User();
        user.setUserAccount(signUpRequest.getUserAccount());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setStatus(1);//預設為
        userRepository.save(user);
        return "註冊成功";
    }
}
