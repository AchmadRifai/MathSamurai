/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.dao;

import achmad.rifai.mathsamurai.dao.entity.Nilai;
import achmad.rifai.mathsamurai.util.Db;
import achmad.rifai.mathsamurai.util.Soal;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ashura
 */
public class DAONilai implements DAO<Nilai>{
    private Db d;

    public DAONilai(Db d) {
        this.d = d;
    }

    @Override
    public void createTable() throws SQLException {
        d.masuk("create table nilai(nama text primary key,level int not null,nyawa int not null,gold int not null,"
                + "exp int not null,tipe text not null,tgl date not null)");
    }

    @Override
    public void insert(Nilai v) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("insert into nilai values(?,?,?,?,?,?,?)");
        p.setString(1, v.getNama());
        p.setInt(2, v.getLevel());
        p.setInt(3, v.getNyawa());
        p.setInt(4, v.getGold());
        p.setInt(5, v.getExp());
        p.setString(6, ""+v.getTipe());
        p.setDate(7, v.getTgl());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Nilai w) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("delete from nilai where nama=?");
        p.setString(1, w.getNama());
        p.execute();
        p.close();
    }

    @Override
    public void update(Nilai a, Nilai w) throws SQLException {
        //java.sql.PreparedStatement p=d.getPrep("update nilai set level=?");
    }

    @Override
    public List<Nilai> all() throws SQLException {
        List<Nilai>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.keluar("select*from nilai");
        while(r.next()){
            Nilai n=new Nilai();
            n.setExp(r.getInt("exp"));
            n.setGold(r.getInt("gold"));
            n.setLevel(r.getInt("level"));
            n.setNama(r.getString("nama"));
            n.setNyawa(r.getInt("nyawa"));
            n.setTgl(r.getDate("tgl"));
            n.setTipe(Soal.TipeSoal.valueOf(r.getString("tipe")));
            l.add(n);
        } r.close();
        l.sort(sortinge());
        return l;
    }

    private Comparator<? super Nilai> sortinge() {
        return (Nilai o1, Nilai o2) -> {
            int i,a=genNilai(o1),b=genNilai(o2);
            if(a>b)i=1;
            else if(a<b)i=-1;
            else i=0;
            return i;
        };
    }

    private int genNilai(Nilai o1) {
        int i=o1.getGold()+o1.getExp()*o1.getNyawa()*o1.getLevel();
        if(o1.getTipe()==Soal.TipeSoal.SULIT)i*=10;
        else if(o1.getTipe()==Soal.TipeSoal.SEDANG)i*=5;
        return i;
    }
}