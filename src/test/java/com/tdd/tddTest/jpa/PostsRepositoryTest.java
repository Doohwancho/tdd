package com.tdd.tddTest.jpa;

import com.tdd.tddTest.domain.PostsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void postsRepository_의존성_주입_테스트(){
        Assertions.assertTrue(!postsRepository.getClass().getName().isEmpty());
    }
}
