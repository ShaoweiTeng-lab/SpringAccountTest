package div.project.springaccounttest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import div.project.springaccounttest.dto.request.*;
import div.project.springaccounttest.dto.response.UserProfile;
import div.project.springaccounttest.vo.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
public interface UserService {
    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest) throws JsonProcessingException;

    UserProfile getUserProfile(Integer userId);
    List<User>  getUser(UserQueryRequest userQueryRequest);

    String deleteUser(Integer userId);
    String adjustPassword(Integer userId,AdjustPasswordRequest adjustPasswordRequest);
    String  adjustUserStatus(AdjustUserStatusRequest adjustProfileRequest);
}
