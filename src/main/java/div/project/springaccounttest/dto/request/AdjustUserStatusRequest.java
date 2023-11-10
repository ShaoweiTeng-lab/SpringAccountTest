package div.project.springaccounttest.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AdjustUserStatusRequest {
    private Integer userId;
    @Min(value = 0,message = "status 不可小於0")
    @Max(value = 0,message = "status 不可大於1")
    private Integer status;
}
