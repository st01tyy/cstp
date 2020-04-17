package edu.bistu.cstp.service.impl;

import edu.bistu.cstp.dao.entity.Goods;
import edu.bistu.cstp.dao.entity.User;
import edu.bistu.cstp.dao.repository.GoodsRepository;
import edu.bistu.cstp.dao.repository.UserRepository;
import edu.bistu.cstp.domain.goods.GoodsInfo;
import edu.bistu.cstp.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService
{
    private GoodsRepository goodsRepository;
    private UserRepository userRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, UserRepository userRepository)
    {
        this.goodsRepository = goodsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GoodsInfo[] getGoodsByOwner(String ownerName)
    {
        try
        {
            User owner = userRepository.findUserByUsername(ownerName);
            List<Goods> goodsList = goodsRepository.findAllByOwner(owner);
            GoodsInfo[] goodsInfoArr = new GoodsInfo[goodsList.size()];
            int i = 0;
            for(Goods goods: goodsList)
            {
                goodsInfoArr[i] = new GoodsInfo();
                BeanUtils.copyProperties(goods, goodsInfoArr[i]);
                i++;
            }
            return goodsInfoArr;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GoodsInfo addNewGoods(GoodsInfo goodsInfo, String username)
    {
        try
        {
            User user = userRepository.findUserByUsername(username);
            Goods goods = new Goods();
            BeanUtils.copyProperties(goodsInfo, goods);
            goods.setOwner(user);
            user.getGoodsSet().add(goods);
            goodsRepository.save(goods);
            return GoodsInfo.successMsg;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return GoodsInfo.failMsg;
        }
    }

    @Override
    public GoodsInfo editGoods(GoodsInfo goodsInfo)
    {
        try
        {
            Goods goods = goodsRepository.findByGid(goodsInfo.getGid());
            BeanUtils.copyProperties(goodsInfo, goods);
            goodsRepository.save(goods);
            return GoodsInfo.successMsg;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return GoodsInfo.failMsg;
        }
    }
}
