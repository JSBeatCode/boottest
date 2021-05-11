package com.boottest.app.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();;
    }

    @Test
    public void test_saveAndSelect(){
        String title = "테스트 게시글";
        String content = "테스트 본문문";

        postsRepository.save(Posts.builder().title(title).content(content).author("jsd").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntity_Insert(){
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out.println(now);
        System.out.println(">>>>>> createData = "+posts.getCreatedData()+", modifiedDate = "+posts.getModifiedDate());

        assertThat(posts.getCreatedData()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
