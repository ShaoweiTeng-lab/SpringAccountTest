package div.project.springaccounttest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import div.project.springaccounttest.dto.LoginRequest;
import div.project.springaccounttest.dto.SignUpRequest;

public interface UserService {
    String signUp(SignUpRequest signUpRequest);
    String login(LoginRequest loginRequest) throws JsonProcessingException;
}
