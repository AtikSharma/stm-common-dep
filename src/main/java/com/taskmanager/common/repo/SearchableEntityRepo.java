package com.taskmanager.common.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchableEntityRepo<E, T> extends JpaRepository<E, T>, JpaSpecificationExecutor<E> {

}
