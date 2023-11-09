package div.project.springaccounttest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import div.project.springaccounttest.dto.request.LoginRequest;
import div.project.springaccounttest.dto.request.SignUpRequest;
import div.project.springaccounttest.dto.response.UserProfile;

public interface UserService {
    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest) throws JsonProcessingException;

    UserProfile getUserProfile(Integer userId);
}
