package edu.bistu.cstp.service.impl;

import edu.bistu.cstp.dao.entity.Goods;
import edu.bistu.cstp.dao.entity.Order;
import edu.bistu.cstp.dao.entity.OrderStatus;
import edu.bistu.cstp.dao.entity.User;
import edu.bistu.cstp.dao.repository.GoodsRepository;
import edu.bistu.cstp.dao.repository.OrderRepository;
import edu.bistu.cstp.dao.repository.UserRepository;
import edu.bistu.cstp.domain.goods.GoodsInfo;
import edu.bistu.cstp.domain.order.OrderCreateRequest;
import edu.bistu.cstp.domain.order.OrderInfo;
import edu.bistu.cstp.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService
{
    private OrderRepository orderRepository;
    private GoodsRepository goodsRepository;
    private UserRepository userRepository;

    private Map<Integer, Object> goodsLockMap;

    @Resource(name = "putLock")
    private Object putLock;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GoodsRepository goodsRepository,
                            UserRepository userRepository, Map<Integer, Object> goodsLockMap)
    {
        this.orderRepository = orderRepository;
        this.goodsRepository = goodsRepository;
        this.userRepository = userRepository;
        this.goodsLockMap = goodsLockMap;
    }

    private Object getGoodsLock(Integer gid)
    {
        Object object = goodsLockMap.get(gid);
        if(object == null)
        {
            synchronized (putLock)
            {
                object = goodsLockMap.get(gid);
                if(object == null)
                {
                    object = new Object();
                    goodsLockMap.put(gid, object);
                }
            }
        }
        return object;
    }

    @Override
    public OrderInfo createNewOrder(OrderCreateRequest orderCreateRequest) throws Exception
    {
        Object goodsLock = getGoodsLock(orderCreateRequest.getGid());
        synchronized (goodsLock)
        {
            Goods goods;
            User buyer;
            try
            {
                goods = goodsRepository.findByGid(orderCreateRequest.getGid());
                buyer = userRepository.findUserByUsername(orderCreateRequest.getBuyer());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return OrderInfo.ERROR;
            }

            Order order = new Order();

            System.out.println("订单需求数量： " + orderCreateRequest.getAmount() + "商品剩余数量：" + goods.getAmount());

            if(orderCreateRequest.getAmount() > goods.getAmount())
                throw new Exception("商品数量不足");

            BeanUtils.copyProperties(orderCreateRequest, order);

            order.setBuyer(buyer);
            buyer.getOrders().add(order);

            order.setGoods(goods);
            goods.getOrders().add(order);

            goods.setAmount(goods.getAmount() - order.getAmount());

            order.setTotalPrice(order.getAmount() * goods.getPrice());
            order.setOrderStatus(OrderStatus.PAYING);

            try
            {
                orderRepository.save(order);
                userRepository.save(buyer);
                goodsRepository.save(goods);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return OrderInfo.ERROR;
            }

            return OrderInfo.SUCCEED;
        }
    }

    @Override
    public OrderInfo buyerPay(OrderInfo orderInfo, String buyer)
    {
        if(!orderInfo.getBuyer().equals(buyer))
            return OrderInfo.ERROR;
        try
        {
            Order order = orderRepository.findByOid(orderInfo.getOid());
            order.setOrderStatus(OrderStatus.SENDING);
            orderRepository.save(order);
            return OrderInfo.SUCCEED;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return OrderInfo.ERROR;
        }
    }

    @Override
    public OrderInfo ownerSend(OrderInfo orderInfo, String owner)
    {
        if(!orderInfo.getOwner().equals(owner))
            return OrderInfo.ERROR;
        try
        {
            Order order = orderRepository.findByOid(orderInfo.getOid());
            order.setOrderStatus(OrderStatus.RECEIVING);
            orderRepository.save(order);
            return OrderInfo.SUCCEED;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return OrderInfo.ERROR;
        }
    }

    @Override
    public OrderInfo buyerReceive(OrderInfo orderInfo, String buyer)
    {
        if(!orderInfo.getBuyer().equals(buyer))
            return OrderInfo.ERROR;
        try
        {
            Order order = orderRepository.findByOid(orderInfo.getOid());
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
            return OrderInfo.SUCCEED;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return OrderInfo.ERROR;
        }
    }


    private OrderInfo[] getOrderInfoArr(List<Order> orders)
    {
        OrderInfo[] orderInfoArr = new OrderInfo[orders.size()];
        for(int i = 0; i < orderInfoArr.length; i++)
        {
            orderInfoArr[i] = getOrderInfoFromOrder(orders.get(i));
        }
        return orderInfoArr;
    }

    @Override
    public OrderInfo[] getOrdersByBuyer(String username)
    {
        try
        {
            User buyer = userRepository.findUserByUsername(username);
            List<Order> orders = orderRepository.findAllByBuyer(buyer);
            return getOrderInfoArr(orders);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderInfo[] getOrdersByGoods(Integer gid)
    {
        try
        {
            Goods goods = goodsRepository.findByGid(gid);
            List<Order> orders = orderRepository.findAllByGoods(goods);
            return getOrderInfoArr(orders);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private OrderInfo getOrderInfoFromOrder(Order order)
    {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(order, orderInfo);
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setTitle(order.getGoods().getTitle());
        goodsInfo.setGid(order.getGoods().getGid());
        orderInfo.setGoods(goodsInfo);
        return orderInfo;
    }

    private OrderInfo getOrderInfoFromOrderInDetail(Order order)
    {
        OrderInfo orderInfo = getOrderInfoFromOrder(order);
        orderInfo.setBuyer(order.getBuyer().getUsername());
        orderInfo.setOwner(order.getGoods().getOwner().getUsername());
        return orderInfo;
    }

    @Override
    public OrderInfo getOrderByOid(Integer oid, String username)
    {
        try
        {
            /*验证访问权限*/
            User user = userRepository.findUserByUsername(username);
            if(user == null)
                return null;
            Order order = orderRepository.findByOid(oid);
            if(order.getBuyer().getUid() != user.getUid() && order.getGoods().getOwner().getUid() != user.getUid())
                return null;

            return getOrderInfoFromOrderInDetail(order);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
