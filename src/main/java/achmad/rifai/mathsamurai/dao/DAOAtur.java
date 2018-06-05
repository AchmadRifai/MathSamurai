/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.dao;

import achmad.rifai.mathsamurai.dao.entity.Atur;
import achmad.rifai.mathsamurai.util.Db;
import achmad.rifai.mathsamurai.util.Soal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ashura
 */
public class DAOAtur implements DAO<Atur>{
    private Db d;

    public DAOAtur(Db d) {
        this.d = d;
    }

    @Override
    public void createTable() throws SQLException {
        d.masuk("create table atur(vol int not null,tipe text not null)");
    }

    @Override
    public void insert(Atur v) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("insert into atur values(?,?)");
        p.setInt(1, v.getVol());
        p.setString(2, ""+v.getTipe());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Atur w) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("delete from atur where vol=? and tipe=?");
        p.setInt(1, w.getVol());
        p.setString(2, ""+w.getTipe());
        p.execute();
        p.close();
    }

    @Override
    public void update(Atur a, Atur w) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("update atur set vol=?,tipe=? where vol=? and tipe=?");
        p.setInt(1, w.getVol());
        p.setString(2, ""+w.getTipe());
        p.setInt(3, a.getVol());
        p.setString(4, ""+a.getTipe());
        p.execute();
        p.close();
    }

    @Override
    public List<Atur> all() throws SQLException {
        List<Atur>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.keluar("select*from atur");
        while(r.next()){
            Atur a=new Atur();
            a.setTipe(Soal.TipeSoal.valueOf(r.getString("tipe")));
            a.setVol(r.getInt("vol"));
            l.add(a);
        } r.close();
        return l;
    }
}