package div.project.springaccounttest.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import div.project.springaccounttest.service.imp.auth.UserDetailsImp;
import div.project.springaccounttest.utils.UserJwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserJwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserJwtUtil userJwtUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
//        //URL 路徑檢查
        if (!requestURI.startsWith("/user")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            //若無 token 依然可放行，因沒傳入 security context ，之後的filter會throw AuthenticationException
            filterChain.doFilter(request, response);
            return;
        }
        String userId = null;
        //驗證 jwt
        Claims claims = userJwtUtil.validateToken(token);
        if (claims == null) {
            //返回 null 代表驗證失敗
            filterChain.doFilter(request, response);
            return;
        }
        //取得sub
        userId = claims.getSubject();
        //拿回 redis 儲存的 已經認證的principal
        String userLoginJson = redisTemplate.opsForValue().get("User:Login:" + userId);
        if (userLoginJson == null) {
            //被最高管理員修改後 會須重新登入
            filterChain.doFilter(request, response);
            return;
        }
        UserDetailsImp userDetail = null;
        userDetail = objectMapper.readValue(userLoginJson, UserDetailsImp.class);
        if(userDetail.getUser().getStatus()==0){
            //被停權
            filterChain.doFilter(request,response);
            return;
        }
        //認證成功 傳入 SecurityContext  到當前SecurityContextHolder
        UsernamePasswordAuthenticationToken managerAuthentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        //傳入此次請求的安全上下文
        SecurityContextHolder.getContext().setAuthentication(managerAuthentication);
        //設定attribute ，讓controller 獲得
        request.setAttribute("userId", Integer.valueOf(userId));
        //認證通過
        filterChain.doFilter(request, response);
    }
}
