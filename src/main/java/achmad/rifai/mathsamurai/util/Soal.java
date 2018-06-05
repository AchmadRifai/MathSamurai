/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.util;

/**
 *
 * @author ashura
 */
public class Soal {

    public int getAngka1() {
        return angka1;
    }

    public void setAngka1(int angka1) {
        this.angka1 = angka1;
    }

    public int getAngka2() {
        return angka2;
    }

    public void setAngka2(int angka2) {
        this.angka2 = angka2;
    }

    public TipeSoal getTipe() {
        return tipe;
    }

    public void setTipe(TipeSoal tipe) {
        this.tipe = tipe;
    }

    public SoalOperasi getOperasi() {
        return operasi;
    }

    public void setOperasi(SoalOperasi operasi) {
        this.operasi = operasi;
    }
    public enum TipeSoal{
        MUDAH,SEDANG,SULIT
    }

    public enum SoalOperasi{
        TAMBAH,KURANG,KALI,BAGI
    }

    private int angka1,angka2;
    private TipeSoal tipe;
    private SoalOperasi operasi;

    public float hasil(){
        float f=angka1;
        if(null!=operasi)switch (operasi) {
            case TAMBAH:
                f+=angka2;
                break;
            case BAGI:
                f/=angka2;
                break;
            case KALI:
                f*=angka2;
                break;
            case KURANG:
                f-=angka2;
                break;
            default:
                break;
        } return f;
    }

    public boolean benar(float jawab){
        return hasil()==jawab;
    }

    @Override
    public String toString() {
        String s=""+angka1;
        if(operasi==SoalOperasi.TAMBAH)s+=" + ";
        else if(operasi==SoalOperasi.KURANG)s+=" - ";
        else if(operasi==SoalOperasi.KALI)s+=" * ";
        else if(operasi==SoalOperasi.BAGI)s+=" / ";
        return s+angka2+" = ";
    }
}
