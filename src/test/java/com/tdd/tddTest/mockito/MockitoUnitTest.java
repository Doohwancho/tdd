package com.tdd.tddTest.mockito;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertFalse;

public class MockitoUnitTest {

    @Mock
    Posts post;

    @Mock
    User user;

    @Test
    @DisplayName("this is stub")
    public void makeStubTest(){
//        MockitoAnnotations.initMocks(this);
        Posts post = mock(Posts.class);
        assertTrue(post != null);

        when(post.getTitle()).thenReturn("mocked-title");
        when(post.getContent()).thenReturn("mocked-content");
        when(post.getAuthor()).thenReturn("mocked-author");

        assertTrue(post.getTitle().equals("mocked-title"));
        assertTrue(post.getContent().equals("mocked-content"));
        assertTrue(post.getAuthor().equals("mocked-author"));
    }

    @Test
    @DisplayName("doThrow() test")
    public void doThrowTest(){
        Posts post = mock(Posts.class);
        assertTrue(post != null);

        doThrow(IllegalStateException.class).when(post).getCreatedDate(); //new RuntimeException()

        assertThrows(IllegalStateException.class, () -> {
            post.getCreatedDate();
        });
    }


    @Test
    @DisplayName("verify() 검증 테스트")
    public void verifyMockTest(){
        User user = mock(User.class);
        user.setName("doohwan");

        //setName()이 1번 호출했는지 확인
        verify(user, times(1)).setName(any(String.class));

        //setName()호출 시, 파라미터가 doohwan이었는지 확인
//        verify(user, never()).setName("doohwan"); //false
        verify(user, never()).setName("something else");

        //getName()은 한번도 호출 안되었다
        verify(user, never()).getName();

        //setName()이 최소 1번 이상 호출되었는지 확인
        verify(user, atLeastOnce()).setName(any(String.class));

        //setName()이 최대 2번 이하 호출되었는지 확인
        verify(user, atMost(2)).setName(any(String.class));

        //setName()이 지정된 millisecond 안에 호출되었는지 확인
        verify(user, timeout(100)).setName(any(String.class));

        //setName()이 지정된 millisecond 안에 최소 1번 이상 호출되었는지 확인
        verify(user, timeout(100).atLeast(1)).setName(any(String.class));
    }
}
