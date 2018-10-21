package me.whiteship.springdatademo;

import org.hibernate.boot.jaxb.SourceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() throws ExecutionException, InterruptedException {
        /*Comment comment = new Comment();
        comment.setComment("Hello Comment");

        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);

        Optional<Comment> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty();

        Comment comment1 = byId.orElse(new Comment());*/

        this.createComment(100, "spring data jpa");
        this.createComment(55, "hibernate spring");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likes"));

        /*Page<Comment> spring = commentRepository.findByCommentContainsIgnoreCase
                ("Spring", pageRequest);
        spring.forEach(System.out::println);
        assertThat(spring.getNumberOfElements()).isEqualTo(2);
        assertThat(spring).first().hasFieldOrPropertyWithValue("likes", 100);*/

        ListenableFuture<List<Comment>> future =
                commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        System.out.println("=====================");
        System.out.println("is done?" + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(@Nullable List<Comment> result) {

            }
        });
    }

    private void createComment(int likes, String comment) {
        Comment newcomment = new Comment();
        newcomment.setComment("spring data jpa");
        newcomment.setLikes(likes);
        commentRepository.save(newcomment);
    }
}