package com.example.actividad03_anibal_benedicto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int city_id;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "country_id")
    private int country_id;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
    @Basic
    @Column(name = "last_update")
    private LocalDateTime last_update;

    public City() {
    }
    public City(int city_id, String city, int country_id, LocalDateTime last_update) {
        this.city_id = city_id;
        this.city = city;
        this.country_id = country_id;
        this.last_update = last_update;
    }
    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }
}


