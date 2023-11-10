package div.project.springaccounttest.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdjustPasswordRequest {
    @NotBlank
    private  String password;
}
