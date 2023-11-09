package div.project.springaccounttest.dao;

import div.project.springaccounttest.vo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    /**
     * 如果 :user_Name，則所有的"user"資料都會被選擇。
     * 如果 :user_Name 不為NULL，則"user_Name"資料中的"user_Name"必須包含"name"的值（不區分大小寫）。
     * 根據Pageable参数進行分頁
     * */
    @Query(value = "SELECT * FROM `user` u WHERE (?1 IS NULL OR u.user_Account LIKE CONCAT('%', ?1, '%'))",nativeQuery = true)
    Page<User> findUser(String name, Pageable pageable);

}
