package div.project.springaccounttest.dao;

import div.project.springaccounttest.vo.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    @Modifying
    @Query(value = "INSERT INTO user_role (user_Id, role_id) " +
            "SELECT :userId, r.role_id " +
            "FROM `role` r " +
            "WHERE r.role_name = :roleName", nativeQuery = true)
    void insertUserRole(@Param("userId") Integer userId, @Param("roleName") String roleName);


    @Modifying
    @Query(value = "delete from user_role ur where ur.user_Id = ?1", nativeQuery = true)
    void deleteAllRolesById(Integer userId);
}
