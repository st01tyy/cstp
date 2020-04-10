package edu.bistu.cstp.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(unique = true, nullable = false, length = 10)
    private String username;

    @Column(nullable = false)
    private String pw;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Goods> goodsSet;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;
}
