package div.project.springaccounttest.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private  Integer roleId;
    @Column(name = "role_name")
    private  String roleName;
}
