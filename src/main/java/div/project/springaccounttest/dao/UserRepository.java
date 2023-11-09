package div.project.springaccounttest.dao;

import div.project.springaccounttest.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByUserAccount(String account);

    @Query(value = "SELECT r.role_name " +
            "FROM `user` u " +
            "JOIN user_role ur ON u.user_id = ur.user_id " +
            "JOIN `role` r ON ur.role_id = r.role_id " +
            "WHERE u.user_id = ?1", nativeQuery = true)
    List<String> findUserRolesById(Integer userId);



}
