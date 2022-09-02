package com.tdd.tddTest.jpa;

import com.tdd.tddTest.domain.Posts;
import com.tdd.tddTest.domain.PostsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    @DisplayName("repository 의존성 주입")
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



    @Test
    @DisplayName("게시글 저장")
    public void 게시글_저장(){
        //given
        String title = "hello";
        String content = "hello world";
        String author = "cho";

        Posts post = new Posts()
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        //when
        Posts result = postsRepository.save(post);


        //then
        assertEquals(result.getTitle(), title);
    }

    @Test
    @DisplayName("게시글 제목으로 조회")
    public void 게시글_제목으로_조회(){
        //given
        String title = "unique-title";
        String content = "hello world2";
        String author = "cho2";

        Posts post = new Posts()
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(post);

        //when
        Posts result = postsRepository.findByTitle(title);

        //then
        assertEquals(post.getTitle(), result.getTitle());
    }


    @Test
    @DisplayName("전체 게시글 조회 DESC by ID")
    public void 전체_게시글_조회_DESC_BY_ID(){
        //given
        String title1 = "hello1";
        String content1 = "hello world1";
        String author1 = "cho1";

        Posts post1 = new Posts()
                .builder()
                .title(title1)
                .content(content1)
                .author(author1)
                .build();

        String title2 = "hello2";
        String content2 = "hello world2";
        String author2 = "cho2";

        Posts post2 = new Posts()
                .builder()
                .title(title2)
                .content(content2)
                .author(author2)
                .build();

        postsRepository.save(post1);
        postsRepository.save(post2);

        //when
        List<Posts> result = postsRepository.findAllDesc();

        //then
        assertAll(
                () -> assertNotNull(result.get(0)),
                () -> assertNotNull(result.get(1)),
                () -> assertThat(result.get(0).getId() > result.get(1).getId())
        );
    }

    @Test
    @DisplayName("게시글 업데이트 테스트")
    public void 게시글_업데이트_테스트(){
        //given
        String title = "prev_title";
        String content = "prev_content";
        String author = "author";

        Posts post = new Posts()
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(post);
        Posts prevPost = postsRepository.findByTitle(title);


        //when
        prevPost.update("updated_title","updated_content"); // db 저장시 id 가 같으면 수정처리함
        Posts updatedPost = postsRepository.save(prevPost);


        //then
        assertAll(
                () -> assertThat(updatedPost.getClass().getName()).isNotNull(),
                () -> assertEquals(updatedPost.getAuthor(), prevPost.getAuthor()),
                () -> assertEquals(updatedPost.getTitle(), "updated_title")
        );
    }

    @Test
    @DisplayName("게시글 삭제")
    public void 게시글_삭제_테스트(){
        //given
        String title = "hello";
        String content = "hello world";
        String author = "cho";

        Posts post = new Posts()
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        Posts result = postsRepository.save(post);
        long id = result.getId();

        //when
        postsRepository.deleteById(id);

        //then
        assertThat(postsRepository.findById(id)).isEmpty();
    }
}
