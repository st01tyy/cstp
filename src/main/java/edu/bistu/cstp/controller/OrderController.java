package edu.bistu.cstp.controller;

import edu.bistu.cstp.domain.order.OrderCreateRequest;
import edu.bistu.cstp.domain.order.OrderInfo;
import edu.bistu.cstp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController
{
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping("/data/new_order")
    public OrderInfo createNewOrder(@RequestBody OrderCreateRequest orderCreateRequest)
    {
        try
        {
            OrderInfo orderInfo = orderService.createNewOrder(orderCreateRequest);
            return orderInfo;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return OrderInfo.ERROR;
        }
    }
}
