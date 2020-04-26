package edu.bistu.cstp.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest
{
    private Integer gid;

    private Integer amount;

    private String buyer;
}
