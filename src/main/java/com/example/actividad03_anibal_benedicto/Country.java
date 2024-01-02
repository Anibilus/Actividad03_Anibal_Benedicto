package com.example.actividad03_anibal_benedicto;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private LocalDateTime last_update;

    public Country() {
    }

    public Country(int country_id, String country, LocalDateTime last_update) {
        this.country_id = country_id;
        this.country = country;
        this.last_update = last_update;
    }
    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }
}

