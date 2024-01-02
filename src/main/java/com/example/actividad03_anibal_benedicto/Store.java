package com.example.actividad03_anibal_benedicto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    @Column(name = "manager_staff_id")
    private int managerStaffId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id", insertable = false, updatable = false)
    private Staff manager;

    @Column(name = "address_id")
    private int addressId;

    @ManyToOne
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address address;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "store")
    private List<Staff> staffList;

    public Store() {
    }

    public Store(int storeId, int managerStaffId, int addressId, LocalDateTime lastUpdate) {
        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
        this.lastUpdate = lastUpdate;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    // Otros métodos getter y setter

    public int getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(int managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public Staff getManager() {
        return manager;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
