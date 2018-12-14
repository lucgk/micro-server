package com.micro.web.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wellben on 15/11/6.
 */
public interface MyBatisCrudRepository<T, ID extends Serializable> {

    int save(T var1);

    <S extends T> int msave(Iterable<S> var1);

    int update(T var1);

    int delete(ID var1);

    int deleteBy(T var1);

    int deleteAll();

    T findOne(ID var1);

    List<T> findAll();

    List<T> findAllBy(Iterable<ID> var1);

    long count();

    boolean exists(ID var1);

}
