package com.deploy.war.demoapp.repository;

import com.deploy.war.demoapp.models.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByPersonID(Integer personID);
    @Query("SELECT n FROM Note n WHERE n.id = :id")
    Note myFindById(@Param("id") long id);
}