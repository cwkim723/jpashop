package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    /*
        [ InheritanceType ]
        SINGLE_TABLE: 한 테이블에 다 때려박음 => 이거 선택할 것
        TABLE_PER_CLASS: BOOK, MOVIE, ALBUM 이렇게 나뉘어져 있음
        JOINED
    */

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

}
