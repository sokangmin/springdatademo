package me.whiteship.springdatademo;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("keesun test");
        account.setPassword("hibernate test");
        account.setDate(new Date());

        Study study = new Study();
        study.setName("Spring Data JPA");

        account.addStudy(study);
        /*account.getStudies().add(study);
        study.setOwner(account);*/


        // ----
        Post post = new Post();
        post.setTitle("Spring Data JPA 언제 보나...");

        Comment comment = new Comment();
        comment.setComment("빨리 보고 싶어요.");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여드릴께요.");
        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);

        //entityManager.persist(account);

        session.save(account);
        session.save(study);


        session.save(post);


        TypedQuery<Post> query = entityManager.createQuery("select p from Post as p", Post.class);
        List<Post> posts = query.getResultList();

        posts.forEach(System.out::println);

    }
}
