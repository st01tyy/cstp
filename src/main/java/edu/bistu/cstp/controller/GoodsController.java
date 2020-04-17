package edu.bistu.cstp.controller;

import edu.bistu.cstp.domain.goods.GoodsInfo;
import edu.bistu.cstp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class GoodsController
{
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService)
    {
        this.goodsService = goodsService;
    }

    @GetMapping("/data/goods")
    public GoodsInfo[] getGoods(HttpSession httpSession)
    {
        String ownerName = (String) httpSession.getAttribute("id");
        if(ownerName == null)
            return null;
        GoodsInfo[] res = goodsService.getGoodsByOwner(ownerName);
        return res;
    }

    @PostMapping("/data/new_goods")
    public GoodsInfo addNewGoods(@RequestBody GoodsInfo goodsInfo, HttpSession httpSession)
    {
        String username = (String) httpSession.getAttribute("id");
        if(username == null)
        {
            return GoodsInfo.failMsg;
        }
        else
        {
            GoodsInfo res = goodsService.addNewGoods(goodsInfo, username);
            return res;
        }
    }

    @PostMapping("/data/edit_goods")
    public GoodsInfo editGoods(@RequestBody GoodsInfo goodsInfo, HttpSession httpSession)
    {
        String username = (String) httpSession.getAttribute("id");
        if(username == null)
        {
            return GoodsInfo.failMsg;
        }
        else
        {
            GoodsInfo res = goodsService.editGoods(goodsInfo);
            return res;
        }
    }
}
