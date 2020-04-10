package edu.bistu.cstp.domain.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo
{
    private String title;

    private String detail;

    private Float price;

    private Integer amount;
}
