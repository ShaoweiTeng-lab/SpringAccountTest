package div.project.springaccounttest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "role_id")
    private Integer roleId;
}
class UserRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;
}