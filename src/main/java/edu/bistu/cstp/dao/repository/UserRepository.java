package edu.bistu.cstp.dao.repository;

import edu.bistu.cstp.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findUserByUsernameAndPw(String username, String pw);
}
