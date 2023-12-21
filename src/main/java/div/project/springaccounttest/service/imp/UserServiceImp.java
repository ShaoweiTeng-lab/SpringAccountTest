package div.project.springaccounttest.service.imp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import div.project.springaccounttest.dao.UserRepository;
import div.project.springaccounttest.dao.UserRoleRepository;
import div.project.springaccounttest.dto.request.*;
import div.project.springaccounttest.dto.response.LoginResponse;
import div.project.springaccounttest.dto.response.RefreshAccessTokenResponse;
import div.project.springaccounttest.dto.response.ResponsePage;
import div.project.springaccounttest.dto.response.UserProfile;
import div.project.springaccounttest.service.UserService;
import div.project.springaccounttest.service.imp.auth.UserDetailsImp;
import div.project.springaccounttest.utils.UserJwtUtil;
import div.project.springaccounttest.utils.UtilsTool;
import div.project.springaccounttest.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        Date now =new Date();
        user.setCreatedDate(now);
        userRepository.save(user);
        userRoleRepository.insertUserRole(user.getUserId(),"會員");//機本權限
        return "註冊成功";
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(loginRequest.getUserAccount(),loginRequest.getPassword());
        Authentication authentication= authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)) //返回空值代表認證失敗
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"認證錯誤");
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        if(userDetailsImp.getUser().getStatus()==0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您已被停權");
        String userId =String.valueOf( userDetailsImp.getUser().getUserId());

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        //獲得權限列表
        Set<String> roles = AuthorityUtils.authorityListToSet(userDetailsImp.getAuthorities());
        map.put("roles",roles);
        String jwt= userJwtUtil.generateJwt(map);
        LoginResponse loginResponse= new LoginResponse();
        loginResponse.setAccessToken(jwt);
        //生成refresh token
        String refreshToken= UtilsTool.generateUUID();
        loginResponse.setRefreshToken(refreshToken);

        //存入 redis並設置30 天
        redisTemplate.opsForValue().set("refreshToken:"+refreshToken,userId);
        redisTemplate.expire("refreshToken:"+refreshToken,30, TimeUnit.DAYS);
        return  loginResponse;
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

    @Override
    public ResponsePage<List<User>> getUser(UserQueryRequest userQueryRequest) {
        Sort sort =null ;//設定排序方式
        switch (userQueryRequest.getSort()){
            case asc:
                sort = Sort.by(Sort.Order.asc(userQueryRequest.getOrder().name()));
                break;
            case desc:
                sort = Sort.by(Sort.Order.desc(userQueryRequest.getOrder().name()));
                break;
        }
        //創建Pageable   頁碼從0開始
        Pageable pageable = PageRequest.of(userQueryRequest.getPage()-1, userQueryRequest.getSize(), sort);
        //執行查詢返回 Page
        Page userPage = userRepository.findUser(userQueryRequest.getSearch(), pageable);
        ResponsePage<List<User>> rs =new ResponsePage<>();
        rs.setPage(userPage.getPageable().getPageNumber()+1);//pageable預設從第0頁開始
        rs.setSize(pageable.getPageSize());
        rs.setTotal((int)userPage.getTotalElements());
        List<User> userList=userPage.getContent();
        rs.setBody(userList);
        return rs;
    }
    @Transactional
    @Override
    public String deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使用者");
        user.getRoles().forEach(role->{
            if(role.getRoleName().equals("管理員"))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"不可刪除最高管理員");
        });
        userRoleRepository.deleteAllRolesById(userId);//先刪除相關權限
        userRepository.deleteById(userId);
        redisTemplate.delete("User:Login:"+userId);
        return "刪除成功";
    }

    @Override
    public String adjustPassword(Integer userId, AdjustPasswordRequest adjustPasswordRequest) {
        User user = userRepository.findById(userId).orElse(null);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使用者");
        user.setPassword(bCryptPasswordEncoder.encode(adjustPasswordRequest.getPassword()));
        userRepository.save(user);
        return "修改成功";
    }

    @Override
    public String adjustUserStatus(AdjustUserStatusRequest adjustProfileRequest) {
        User user = userRepository.findById(adjustProfileRequest.getUserId()).orElse(null);
        if(user==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使用者");
        user.getRoles().forEach(role->{
            if(role.getRoleName().equals("管理員"))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"不可更改管理員權限");
        });
        user.setStatus(adjustProfileRequest.getStatus());
        userRepository.save(user);
        redisTemplate.delete("User:Login:"+user.getUserId());//帳號被修改後須重新登入
        return "修改成功";
    }

    @Override
    public RefreshAccessTokenResponse refreshAccessToken(String refreshToken) {

        String userId= redisTemplate.opsForValue().get("refreshToken:"+refreshToken);
        if(userId==null)
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"無法取得AccessToken");
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        List<String> roles=userRepository.findUserRolesById(Integer.parseInt(userId));
        map.put("roles",roles);
        String jwt= userJwtUtil.generateJwt(map);
        RefreshAccessTokenResponse response =new RefreshAccessTokenResponse();
        response.setAccessToken(jwt);
        return response;
    }
}
