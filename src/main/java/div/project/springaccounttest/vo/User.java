package div.project.springaccounttest.vo;

import lombok.Data;

import javax.persistence.*;
import  java.util.*;

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
    private  String password;
    private  Integer status;
//    @OneToMan
//    private  List<Role> roles;
}
