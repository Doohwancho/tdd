package com.tdd.tddTest.mockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {
    @Autowired
    private IndexController indexController;

    @Autowired
    private PostsApiController apiController;

    @Autowired
    private MockMvc indexMockMvc;

    @Autowired
    private MockMvc apiMockMvc;

    @BeforeEach
    public void init(){
        indexMockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        apiMockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    @DisplayName("IndexController에 / request 테스트")
    public void httpGetTest() throws Exception{
        indexMockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("PostsApiController에 POST 작동 테스트")
    public void httpPostTest() throws Exception{

        apiMockMvc.perform(post("/api/v1/posts")
                        .content("{\"title\":\"post-test-title\", \"content\":\"post--test-content\", \"author\":\"post-test-author\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}


//.accept(MediaType.APPLICATION_JSON))

/*
---\
http method 별

mock.perform(get("/test")).andExpect(status().isOk()).andDo(print());
mock.perform(post("/test")).andExpect(status().isOk()).andDo(print());
mock.perform(put("/test")).andExpect(status().isOk()).andDo(print());
mock.perform(delete("/test")).andExpect(status().isOk()).andDo(print());


---\
parameter for GET

(single param)
mock.perform(get("/test").param("name","jisu")).andExpect(status().isOk()).andDo(print());

(multiple param)

mock.perform(get("/test").params(MultiValueMap<String, String> data).andExpect(status().isOk()).andDo(print());

MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
    data.add("name", "jisu");
    data.add("age", "25");
    data.add("address", "Incheon");
    data.add("githubId", "geesuee");
mock.perform(get("/test").params(data)).andExpect(status().isOk()).andDo(print());


---\
@RequestBody (Post)

mock.perform(post("/test")
   .content("{\"name\":\"jisu\", \"age\":\"25\", \"address\":\"Incheon\", \"githubId\":\"geesuee\"}")
   .contentType(MediaType.APPLICATION_JSON)
   .accept(MediaType.APPLICATION_JSON))
   .andExpect(status().isOk())
   .andDo(print());


---
method: MockMvcRequestBuilders

- param | params
- header | headers
- cookie
- content
- requestAttr
- flashAttr
- sessionAttr

---
method: ResultMatcher(실행 결과 검증)

- status
- header
- cookie
- content
- view
- forwardedUrl
- redirectUrl
- model - spring mvc 모델 상태 검증
- flash
- request


---
method: ResultHandler(실행 결과 출력)

- log
- print



 */
