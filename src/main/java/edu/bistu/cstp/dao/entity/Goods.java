package edu.bistu.cstp.dao.entity;

import lombok.Data;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Goods
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false, scale = 2)
    private Float price;

    @Column(nullable = false)
    @Check(constraints = "amount >= 0")
    private Integer amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User owner;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders;
}
