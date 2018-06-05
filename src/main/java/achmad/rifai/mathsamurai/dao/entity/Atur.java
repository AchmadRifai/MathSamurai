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
public class Atur {
    private int vol;
    private Soal.TipeSoal tipe;

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public Soal.TipeSoal getTipe() {
        return tipe;
    }

    public void setTipe(Soal.TipeSoal tipe) {
        this.tipe = tipe;
    }
}