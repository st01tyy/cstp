package edu.bistu.cstp.domain.order;

import edu.bistu.cstp.dao.entity.OrderStatus;
import edu.bistu.cstp.domain.goods.GoodsInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo
{
    private Integer oid;

    private Integer amount;

    private Float totalPrice;

    private OrderStatus orderStatus;

    private Date createTime;

    private GoodsInfo goods;

    private String owner;

    private String buyer;

    public final static OrderInfo ERROR = new OrderInfo(-1, null, null,
            null, null, null, null, null);

    public final static OrderInfo SUCCEED = new OrderInfo(0, null, null,
            null, null, null, null, null);
}
