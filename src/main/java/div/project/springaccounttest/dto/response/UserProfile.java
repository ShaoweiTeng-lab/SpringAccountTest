package div.project.springaccounttest.dto.response;

import div.project.springaccounttest.vo.Role;
import lombok.Data;
import java.util.*;


@Data
public class UserProfile {
    private  String account;
    private  Integer status;
    private  List<String> roleList;
}
