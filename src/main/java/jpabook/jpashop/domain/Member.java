package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    // @GeneratedValue: db에서 만든 값을 그대로 쓰겠다.

    private String name;

    @Embedded // 내장 타입
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
