package me.whiteship.springdatademo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {
    <E extends T> E save(E comment);

    <E extends T> List<E>  findAll();

    long count();

    <E extends T> Optional<E> findById(Long id);
}
