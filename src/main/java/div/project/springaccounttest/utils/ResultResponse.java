package div.project.springaccounttest.utils;

import lombok.Data;

@Data
public class ResultResponse<T> {
    private Integer code =200;
    private  T message;
}