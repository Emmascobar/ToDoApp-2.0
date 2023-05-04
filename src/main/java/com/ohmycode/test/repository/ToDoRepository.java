package com.ohmycode.test.repository;

import com.ohmycode.test.models.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    @Query("SELECT t FROM ToDo t WHERE t.title LIKE %?1%")
    public List<ToDo> findAll(String keyWord);

    @Query("SELECT t FROM ToDo t JOIN FETCH t.user u WHERE u.username LIKE %?1%")
    public List<ToDo> findByUsername(String username);


}