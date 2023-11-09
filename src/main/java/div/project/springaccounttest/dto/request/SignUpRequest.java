package div.project.springaccounttest.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank(message = "帳號不可為空")
    private String userAccount;
    @NotBlank(message = "密碼不可為空")
    private  String password;
}
