/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.dao;

/**
 *
 * @author ashura
 */
public interface DAO<T> {
    void createTable() throws java.sql.SQLException;
    void insert(T v) throws java.sql.SQLException;
    void delete(T w) throws java.sql.SQLException;
    void update(T a, T w) throws java.sql.SQLException;
    java.util.List<T> all() throws java.sql.SQLException;
}