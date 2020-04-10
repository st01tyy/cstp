package edu.bistu.cstp.dao.repository;

import edu.bistu.cstp.dao.entity.Order;
import edu.bistu.cstp.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
    List<Order> findAllByBuyer(User buyer); /*查询用户的全部订单*/
}

