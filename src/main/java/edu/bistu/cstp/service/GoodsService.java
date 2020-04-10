package edu.bistu.cstp.service;

import edu.bistu.cstp.domain.goods.GoodsInfo;

public interface GoodsService
{
    GoodsInfo[] getGoodsByOwner(String ownerName);
}
