package com.taskmanager.common.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface SearchableEntityRepo<E, T> extends MongoRepository<E, T>, QueryByExampleExecutor<E> {

}
