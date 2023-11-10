package div.project.springaccounttest.dto.response;

import lombok.Data;

@Data
public class ResponsePage<T> {
    private  Integer  page;//當前頁數
    private  Integer size;//顯示筆數
    private  Integer  total;//總筆數
    private  T body;
}