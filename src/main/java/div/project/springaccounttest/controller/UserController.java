package div.project.springaccounttest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import div.project.springaccounttest.dto.request.LoginRequest;
import div.project.springaccounttest.dto.request.SignUpRequest;
import div.project.springaccounttest.dto.request.UserQueryRequest;
import div.project.springaccounttest.dto.response.UserProfile;
import div.project.springaccounttest.service.UserService;
import div.project.springaccounttest.utils.ResultResponse;
import java.util.*;

import div.project.springaccounttest.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "會員相關")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signUp")
    @ApiOperation("會員註冊")
    public ResultResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        ResultResponse rs =new ResultResponse();
        rs.setMessage(userService.signUp(signUpRequest));
        return  rs;
    }
    @PostMapping("/login")
    @ApiOperation("會員登入")
    public  ResultResponse userLogin(@RequestBody @Valid LoginRequest loginRequest) throws JsonProcessingException {
        ResultResponse rs =new ResultResponse();
        rs.setMessage(userService.login(loginRequest));
        return  rs;
    }


    @ApiOperation("會員查自身資料")
    @GetMapping ("/userProfile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, dataType = "string", paramType = "header")
    })
    public  ResultResponse<UserProfile> getUserProfile(@RequestAttribute("userId")Integer userId)  {
        ResultResponse rs =new ResultResponse();
        rs.setMessage(userService.getUserProfile(userId));
        return  rs;
    }
    /**
     * 最高管理員才可查詢所有會員資訊
     * */
    @PreAuthorize("hasAnyAuthority('管理員')")
    @GetMapping()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, dataType = "string", paramType = "header")
    })
    public  ResultResponse<List<User>> getUser(@ModelAttribute UserQueryRequest userQueryRequest)  {
        ResultResponse rs =new ResultResponse();
        rs.setMessage(userService.getUser(userQueryRequest));
        return  rs;
    }

}
