package div.project.springaccounttest;

import div.project.springaccounttest.utils.UserJwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class JwtTest {
    @Autowired
    UserJwtUtil userJwtUtil;
    @Test
    public  void testAccount(){
        Map<String,Object> map= new HashMap<>();
        map.put("userId",1);
        List<String> roles = Arrays.asList("會員","管理員");
        map.put("roles",roles);
        String jwt= userJwtUtil.generateJwt(map) ;
       Claims claims=  userJwtUtil.validateToken(jwt);
        System.out.println( claims);
    }
}
