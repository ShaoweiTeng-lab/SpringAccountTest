package div.project.springaccounttest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "帳號不可為空")
    private  String userAccount;
    @NotBlank(message = "密碼不可為空")
    private  String password;
}
