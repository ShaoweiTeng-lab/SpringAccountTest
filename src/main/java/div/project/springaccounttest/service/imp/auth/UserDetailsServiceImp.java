package div.project.springaccounttest.service.imp.auth;

import div.project.springaccounttest.dao.UserRepository;
import div.project.springaccounttest.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUserAccount(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使用者");
        }
        List<String> roles=userRepository.findUserRolesById(user.getUserId());

        return new UserDetailsImp(user,roles);
    }
}
