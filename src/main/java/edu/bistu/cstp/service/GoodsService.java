package edu.bistu.cstp.service;

import edu.bistu.cstp.domain.goods.GoodsInfo;

public interface GoodsService
{
    GoodsInfo[] getAllGoods();

    GoodsInfo[] searchGoods(String description);

    GoodsInfo[] getGoodsByOwner(String ownerName);

    GoodsInfo getGoodsByGid(int gid);

    GoodsInfo addNewGoods(GoodsInfo goodsInfo, String username);

    GoodsInfo editGoods(GoodsInfo goodsInfo);
}
