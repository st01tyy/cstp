package edu.bistu.cstp.domain.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo
{
    private Integer gid;

    private String title;

    private String detail;

    private Float price;

    private Integer amount;

    private String owner;

    public final static GoodsInfo successMsg = new GoodsInfo(0, null, null, null, null, null);
    public final static GoodsInfo failMsg = new GoodsInfo(null, null, null, null, null, null);
}
