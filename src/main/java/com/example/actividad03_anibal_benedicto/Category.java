package com.example.actividad03_anibal_benedicto;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private LocalDateTime last_update;
    @OneToMany(mappedBy = "category")
    private List<Film> films;

    public Category() {
    }

    public Category(int category_id, String name, LocalDateTime last_update) {
        this.category_id = category_id;
        this.name = name;
        this.last_update = last_update;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }
}
