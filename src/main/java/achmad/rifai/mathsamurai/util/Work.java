/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.util;

import achmad.rifai.mathsamurai.Konstanta;
import achmad.rifai.mathsamurai.obj.Samurai;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashura
 */
public class Work {
    public static java.io.File f=new java.io.File(Konstanta.DB_PATH);

    public static void initDB() {
        try {
            Db d=new Db();
            new achmad.rifai.mathsamurai.dao.DAONilai(d).createTable();
            new achmad.rifai.mathsamurai.dao.DAOAtur(d).createTable();
            achmad.rifai.mathsamurai.dao.entity.Atur a=new achmad.rifai.mathsamurai.dao.entity.Atur();
            a.setTipe(Soal.TipeSoal.MUDAH);
            a.setVol(10);
            new achmad.rifai.mathsamurai.dao.DAOAtur(d).insert(a);
            d.close();
        } catch (SQLException ex) {
            Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void simpan(String nama, Samurai player) {
        try {
            Db d=new Db();
            achmad.rifai.mathsamurai.dao.entity.Nilai n=new achmad.rifai.mathsamurai.dao.entity.Nilai();
            n.setNama(nama);
            n.setTgl(java.sql.Date.valueOf(LocalDate.now()));
            n.setExp(player.getExp());
            n.setGold(player.getGold());
            n.setLevel(player.getLevel());
            n.setNyawa(player.getNyawa());
            n.setTipe(getThisType());
            new achmad.rifai.mathsamurai.dao.DAONilai(d).insert(n);
            d.close();
        } catch (SQLException ex) {
            Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Soal.TipeSoal getThisType() {
        try {
            Db d=new Db();
            java.util.List<achmad.rifai.mathsamurai.dao.entity.Atur>l=new achmad.rifai.mathsamurai.dao.DAOAtur(d).all();
            achmad.rifai.mathsamurai.dao.entity.Atur a=l.get(0);
            Soal.TipeSoal t=a.getTipe();
            d.close();
            return t;
        } catch (SQLException ex) {
            Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
    }

    public static boolean oleh(String nama) {
        boolean b=false; try {
            Db d=new Db();
            java.sql.PreparedStatement p=d.getPrep("select nama from nilai where nama=?");
            p.setString(1, nama);
            java.sql.ResultSet r=p.executeQuery();
            b=!r.next();
            r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
        } return b;
    }

    public static Soal genSoal(int level) {
        Soal s=new Soal();
        Soal.TipeSoal tipe=Work.getThisType();
        generateSoal(s,tipe,level);
        return s;
    }

    private static void generateSoal(Soal s, Soal.TipeSoal tipe, int level) {
        s.setTipe(tipe);
        int op,awal=1,akhir,add;
        if(tipe==Soal.TipeSoal.MUDAH)op=genRandom(1,2);
        else op=genRandom(1,3);
        if(op==1)s.setOperasi(Soal.SoalOperasi.TAMBAH);
        else if(op==2)s.setOperasi(Soal.SoalOperasi.KURANG);
        else if(op==3)s.setOperasi(Soal.SoalOperasi.KALI);
        if(tipe==Soal.TipeSoal.SULIT)add=10;
        else add=5;
        akhir=add;
        for(int x=1;x<level;x++){
            awal+=add;
            akhir+=add;
        } s.setAngka1(genRandom(awal,akhir));
        s.setAngka2(genRandom(awal,akhir));
    }

    private static int genRandom(int min, int max) {
        java.util.Random r=new java.util.Random();
        return r.nextInt((max-min)+1)+min;
    }
}