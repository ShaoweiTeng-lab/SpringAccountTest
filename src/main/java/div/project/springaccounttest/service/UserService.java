package div.project.springaccounttest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import div.project.springaccounttest.dto.request.AdjustPasswordRequest;
import div.project.springaccounttest.dto.request.LoginRequest;
import div.project.springaccounttest.dto.request.SignUpRequest;
import div.project.springaccounttest.dto.request.UserQueryRequest;
import div.project.springaccounttest.dto.response.UserProfile;
import div.project.springaccounttest.vo.User;
import java.util.*;
public interface UserService {
    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest) throws JsonProcessingException;

    UserProfile getUserProfile(Integer userId);
    List<User>  getUser(UserQueryRequest userQueryRequest);

    String deleteUser(Integer userId);
    String adjustPassword(Integer userId,AdjustPasswordRequest adjustPasswordRequest);
}
