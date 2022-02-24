package com.example.introduction.repository;

import com.example.introduction.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = createMember("spring1");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        log.info("member = {}", member);
        log.info("result = {}", result);

        assertEquals(result, member);
    }

    @Test
    public void findByName() {
        Member member1 = createMember("spring1");
        repository.save(member1);
        Member member2 = createMember("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();

        assertEquals(result1, member1);
        assertEquals(result2, member2);
    }

    @Test
    public void findAll() {
        repository.save(createMember("spring1"));
        repository.save(createMember("spring2"));

        List<Member> result = repository.findAll();

        assertEquals(2, result.size());
    }

    public Member createMember(String name) {
        return Member.builder()
                .name(name)
                .build();
    }
}