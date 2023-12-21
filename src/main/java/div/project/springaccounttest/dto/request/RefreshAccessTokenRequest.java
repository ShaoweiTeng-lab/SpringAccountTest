package div.project.springaccounttest.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshAccessTokenRequest {
    @NotBlank(message = "refreshToken 為空")
    private  String refreshToken;
}
