package com.example.uassalmansqlite.model;

import java.io.Serializable;

public class Students implements Serializable {
    private int id;
    private String nim, nama, hp, studi, email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getStudi() {
        return studi;
    }

    public void setStudi(String studi) {
        this.studi = studi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
