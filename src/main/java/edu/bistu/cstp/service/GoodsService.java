package edu.bistu.cstp.service;

import edu.bistu.cstp.domain.goods.GoodsInfo;

public interface GoodsService
{
    GoodsInfo[] getGoodsByOwner(String ownerName);

    GoodsInfo addNewGoods(GoodsInfo goodsInfo, String username);

    GoodsInfo editGoods(GoodsInfo goodsInfo);
}
