package edu.bistu.cstp.dao.repository;

import edu.bistu.cstp.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query(value = "select * from user where username = :username and pw = :pw", nativeQuery = true)
    User findUserByUsernameAndPw(String username, String pw);
}
