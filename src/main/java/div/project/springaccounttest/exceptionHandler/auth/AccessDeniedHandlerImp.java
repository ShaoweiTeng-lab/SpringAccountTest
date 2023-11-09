package div.project.springaccounttest.exceptionHandler.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import div.project.springaccounttest.utils.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImp  implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper mapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //處理異常 授權失敗 回傳  json格式
        ResultResponse rs =new ResultResponse();
        rs.setCode(403);
        rs.setMessage("您無此權限");
        String json =mapper.writeValueAsString(rs);
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().print(json);
    }
}
