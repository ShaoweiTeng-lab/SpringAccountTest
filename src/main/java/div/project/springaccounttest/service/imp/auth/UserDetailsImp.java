package div.project.springaccounttest.service.imp.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import div.project.springaccounttest.vo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsImp implements UserDetails {
    private User user;
    private List<String> roleList;
    @JsonIgnore
    private List<SimpleGrantedAuthority>authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //管理員權限
        if(authorities!=null)//
            return  authorities;
        authorities =new ArrayList<>();
        for (String permission : roleList) {
            SimpleGrantedAuthority simpleAuthority =new SimpleGrantedAuthority(permission);
            authorities.add(simpleAuthority);
        }
        // 須返回上界為 GrantedAuthority的類 ，Spring Security 有實作  SimpleGrantedAuthority
        return authorities;
    }
    public UserDetailsImp(User user, List<String> roleList) {
        this.user = user;
        //注入權限列表
        this.roleList = roleList;

    }
    @Override
    public String getPassword() {
        //返回使用者密碼 ，在 ProviderManager 跟使用者輸入進行比對
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        //返回使用者帳戶 ，在 ProviderManager 跟使用者輸入進行比對
        return user.getUserAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
