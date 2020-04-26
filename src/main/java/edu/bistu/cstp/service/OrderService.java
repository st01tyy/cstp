package edu.bistu.cstp.service;

import edu.bistu.cstp.domain.order.OrderCreateRequest;
import edu.bistu.cstp.domain.order.OrderInfo;

public interface OrderService
{
    OrderInfo createNewOrder(OrderCreateRequest orderCreateRequest) throws Exception;    /*注意线程安全*/

    OrderInfo buyerPay(OrderInfo orderInfo);

    OrderInfo ownerSend(OrderInfo orderInfo);

    OrderInfo buyerReceive(OrderInfo orderInfo);

    OrderInfo[] getOrdersByBuyer(String username);

    OrderInfo[] getOrdersByGoods(Integer gid);

    OrderInfo getOrderByOid(Integer oid);
}
