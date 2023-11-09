package div.project.springaccounttest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import div.project.springaccounttest.dto.LoginRequest;
import div.project.springaccounttest.dto.SignUpRequest;
import div.project.springaccounttest.service.UserService;
import div.project.springaccounttest.utils.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signUp")
    public ResultResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        ResultResponse rs =new ResultResponse();
        rs.setMessage(userService.signUp(signUpRequest));
        return  rs;
    }
    @PostMapping("/login")
    public  ResultResponse userLogin(@RequestBody @Valid LoginRequest loginRequest) throws JsonProcessingException {
        ResultResponse rs =new ResultResponse();
        rs.setMessage(userService.login(loginRequest));
        return  rs;
    }


    @GetMapping ("/userProfile")
    public  ResultResponse getUserProfile(@RequestAttribute("userId")String userId)  {
        ResultResponse rs =new ResultResponse();
        return  rs;
    }
}
