package div.project.springaccounttest.dao;

import div.project.springaccounttest.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByUserAccount(String account);
}
