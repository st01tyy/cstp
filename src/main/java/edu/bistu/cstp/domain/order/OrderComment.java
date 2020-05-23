package edu.bistu.cstp.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderComment
{
    private Integer oid;    //订单号
    private String buyerComment;    //买家评论
    private String ownerComment;    //卖家评论
}
