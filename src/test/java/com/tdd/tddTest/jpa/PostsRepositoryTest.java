package com.tdd.tddTest.jpa;

import com.tdd.tddTest.domain.Posts;
import com.tdd.tddTest.domain.PostsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    @DisplayName("repository 의존성 주입 테스트")
    public void postsRepository_의존성_주입_테스트(){
        assertThat(postsRepository.getClass().getName()).isNotNull();
    }

    @Test
    @DisplayName("lombok 작동 확인")
    public void 롬복_빌더_작동_여부_확인() {
        //given
        String title = "hello";
        String content = "hello world";
        String author = "cho";

        //when
        Posts post = new Posts()
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        //then
        assertAll(
            () -> assertThat(post.getTitle()).isNotNull(),
            () -> assertThat(post.getTitle()).isEqualTo(title),
            () -> assertThat(post.getContent()).isNotNull(),
            () -> assertThat(post.getContent()).isEqualTo(content),
            () -> assertThat(post.getAuthor()).isNotNull(),
            () -> assertThat(post.getAuthor()).isEqualTo(author)
        );
    }

}
