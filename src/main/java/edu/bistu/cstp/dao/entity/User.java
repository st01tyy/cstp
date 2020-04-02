package edu.bistu.cstp.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 10)
    private String username;

    @Column(nullable = false)
    private String pw;
}
