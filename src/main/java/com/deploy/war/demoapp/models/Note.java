package com.deploy.war.demoapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class Note {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    private String content;
    private int personID;

    public Note() {
    }

    public Note(String theme, String content, int personID) {
        this.theme = theme;
        this.content = content;
        this.personID = personID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int person_ID) {
        this.personID = personID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}