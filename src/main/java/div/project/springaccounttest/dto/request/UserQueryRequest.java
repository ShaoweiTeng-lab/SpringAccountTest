package div.project.springaccounttest.dto.request;

import div.project.springaccounttest.dto.Sort;
import div.project.springaccounttest.dto.UserOrderBy;
import lombok.Data;

@Data
public class UserQueryRequest {
    private  String search;
    private UserOrderBy order =UserOrderBy.USER_ID;
    private Sort sort =Sort.desc ;
    private  Integer page =1;
    private  Integer size =5;
}
