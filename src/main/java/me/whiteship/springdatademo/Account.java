package me.whiteship.springdatademo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Transient
    private String no;

    @Embedded
    private Address address;
}
