package edu.bistu.cstp.controller;

import edu.bistu.cstp.domain.goods.GoodsInfo;
import edu.bistu.cstp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/page/goods_edit")
    public ModelAndView jumpToGoodsEdit(@RequestParam String title, @RequestParam String detail, @RequestParam Float price, @RequestParam Integer amount)
    {
        GoodsInfo goodsInfo = new GoodsInfo(title, detail, price, amount);
        if(goodsInfo != null)
            System.out.println(goodsInfo.toString());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods_edit");
        modelAndView.addObject("goodsInfo", goodsInfo);
        return modelAndView;
    }
}
