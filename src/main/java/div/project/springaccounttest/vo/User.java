package div.project.springaccounttest.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import  java.util.*;
import java.util.Date;
@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @Column(name = "user_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer userId;
    @Column(name = "user_Account")
    private  String userAccount;
    @JsonIgnore
    private  String password;
    private  Integer status;
    @Column(name = "created_Date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "Asia/Taipei")
    private Date createdDate;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(
                    referencedColumnName = "user_Id",
                    name = "user_Id"
            ),
            name = "user_role",
            inverseJoinColumns = @JoinColumn(
                    name="role_id",
                    referencedColumnName = "role_id"
            )
    )
    private  List<Role> roles;
}
