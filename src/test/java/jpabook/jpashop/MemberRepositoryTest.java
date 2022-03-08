package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false) // 롤백 안 하고 커밋해버림
    public void testMember() throws Exception{
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId()); // true
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername()); // true
        Assertions.assertThat(findMember).isEqualTo(member); // true
        System.out.println("findMember == member " + (findMember == member)); // true, 같은 영속성 컨텍스트 안에서 아이디 값이 같으면 같다고 봄

        /*
            application.yml:
            jpa:
                hibernate:
                  ddl-auto: create


            drop sequence if exists hibernate_sequence

            create table member (
               id bigint not null,
                username varchar(255),
                primary key (id)
            )
        */
        /*
            @Rollback(value = false)

            insert
            into
                member
                (username, id)
            values
                (?, ?)
        */

        /*
        implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6'
        insert into member (username, id) values (?, ?)
        insert into member (username, id) values ('memberA', 1);
        */
    }
}