package div.project.springaccounttest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private String userAccount;
    @NotBlank
    private  String password;
}
