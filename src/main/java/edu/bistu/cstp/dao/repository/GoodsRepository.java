package edu.bistu.cstp.dao.repository;

import edu.bistu.cstp.dao.entity.Goods;
import edu.bistu.cstp.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer>
{
    List<Goods> findAllByOwner(User user);  //查询用户发布的所有商品

    List<Goods> findAllByDetailIsLike(String str);  //根据用户输入的关键字查询相关商品
}
