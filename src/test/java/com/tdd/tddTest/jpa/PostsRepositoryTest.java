package com.tdd.tddTest.jpa;

import com.tdd.tddTest.domain.PostsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    @DisplayName("repository 의존성 주입 테스트")
    public void postsRepository_의존성_주입_테스트(){
        assertThat(postsRepository.getClass().getName()).isNotNull();
    }

}
