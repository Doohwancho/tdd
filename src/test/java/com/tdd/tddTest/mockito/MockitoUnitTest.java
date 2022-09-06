package com.tdd.tddTest.mockito;

import com.tdd.tddTest.domain.posts.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MockitoUnitTest {

    @Mock
    Posts post;

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
}
