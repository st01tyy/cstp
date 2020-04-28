package edu.bistu.cstp.service;

import edu.bistu.cstp.domain.order.OrderCreateRequest;
import edu.bistu.cstp.domain.order.OrderInfo;

import javax.servlet.http.HttpSession;

public interface OrderService
{
    OrderInfo createNewOrder(OrderCreateRequest orderCreateRequest) throws Exception;    /*注意线程安全*/

    OrderInfo buyerPay(OrderInfo orderInfo, String buyer);

    OrderInfo ownerSend(OrderInfo orderInfo, String owner);

    OrderInfo buyerReceive(OrderInfo orderInfo, String buyer);

    OrderInfo[] getOrdersByBuyer(String username);

    OrderInfo[] getOrdersByGoods(Integer gid);

    OrderInfo getOrderByOid(Integer oid, String username);
}
