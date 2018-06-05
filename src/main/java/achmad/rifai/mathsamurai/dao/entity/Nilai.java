/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.dao.entity;

import achmad.rifai.mathsamurai.util.Soal;

/**
 *
 * @author ashura
 */
public class Nilai {
    private String nama;
    private int level,nyawa,gold,exp;
    private Soal.TipeSoal tipe;
    private java.sql.Date tgl;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNyawa() {
        return nyawa;
    }

    public void setNyawa(int nyawa) {
        this.nyawa = nyawa;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Soal.TipeSoal getTipe() {
        return tipe;
    }

    public void setTipe(Soal.TipeSoal tipe) {
        this.tipe = tipe;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }
}