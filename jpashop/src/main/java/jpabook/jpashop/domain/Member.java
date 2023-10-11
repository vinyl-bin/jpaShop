package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")  //pk 지정
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")   //member entity의 입장에서는 (order가 참조로 one과 many중 many임) 즉, member는 one, 피참조임.
    private List<Order> orders = new ArrayList<>();
}