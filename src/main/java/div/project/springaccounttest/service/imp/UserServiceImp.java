package div.project.springaccounttest.service.imp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import div.project.springaccounttest.dao.UserRepository;
import div.project.springaccounttest.dao.UserRoleRepository;
import div.project.springaccounttest.dto.request.LoginRequest;
import div.project.springaccounttest.dto.request.SignUpRequest;
import div.project.springaccounttest.dto.response.UserProfile;
import div.project.springaccounttest.service.UserService;
import div.project.springaccounttest.service.imp.auth.UserDetailsImp;
import div.project.springaccounttest.utils.UserJwtUtil;
import div.project.springaccounttest.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.*;

@Service
public class UserServiceImp  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserJwtUtil userJwtUtil;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    @Transactional
    public String signUp(SignUpRequest signUpRequest) {
        User  user  = userRepository.findByUserAccount(signUpRequest.getUserAccount());
        if (user!=null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"已有重複帳號");
        user =new User();
        user.setUserAccount(signUpRequest.getUserAccount());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setStatus(1);//預設為存活
        userRepository.save(user);
        userRoleRepository.insertUserRole(user.getUserId(),"會員");//機本權限
        return "註冊成功";
    }

    @Override
    public String login(LoginRequest loginRequest) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(loginRequest.getUserAccount(),loginRequest.getPassword());
        Authentication authentication= authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)) //返回空值代表認證失敗
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"認證錯誤");
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        if(userDetailsImp.getUser().getStatus()==0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您已被停權");
        String userId =String.valueOf( userDetailsImp.getUser().getUserId());
        String userDetailJson=null;
        userDetailJson=objectMapper.writeValueAsString(userDetailsImp);
        redisTemplate.opsForValue().set("User:Login:"+userDetailsImp.getUser().getUserId(),userDetailJson);
        String jwt= userJwtUtil.createJwt(userId);
        return  jwt;
    }

    @Override
    public UserProfile getUserProfile(Integer userId) {
        User  user  = userRepository.findById(userId).orElse(null);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使使用者");
        UserProfile userProfile =new UserProfile();
        userProfile.setAccount(user.getUserAccount());
        userProfile.setStatus(user.getStatus());
        List<String> roleList =new ArrayList<>();
        user.getRoles().forEach(data-> roleList.add(data.getRoleName()));
        userProfile.setRoleList(roleList);
        return userProfile;
    }
}
