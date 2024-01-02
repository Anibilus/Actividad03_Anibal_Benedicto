package com.example.actividad03_anibal_benedicto;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;


@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventory_id;

    @Column(name = "film_id")
    private int film_id;

    @ManyToOne
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @Column(name = "store_id")
    private int store_id;

    @ManyToOne
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    @Column(name = "last_update")
    private LocalDateTime last_update;

    @OneToMany(mappedBy = "inventory")
    private List<Rental> rentals;

    public Inventory() {
    }

    public Inventory(int inventory_id, int film_id, int store_id, LocalDateTime last_update) {
        this.inventory_id = inventory_id;
        this.film_id = film_id;
        this.store_id = store_id;
        this.last_update = last_update;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
