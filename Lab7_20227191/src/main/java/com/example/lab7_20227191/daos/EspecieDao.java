package com.example.lab7_20227191.daos;

import com.example.lab7_20227191.beans.Especie;
import java.sql.*;
import java.util.ArrayList;

public class EspecieDao extends DaoBase<Especie> {

    // para listar especies
    public ArrayList<Especie> listar() {
        ArrayList<Especie> lista = new ArrayList<>();
        String sql = "SELECT * FROM especie";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Especie e = new Especie();
                e.setIdEspecie(rs.getInt("idespecie"));
                e.setNombre(rs.getString("nombre"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void crear(Especie object) {
        // se deja vacio para la herencia abstracta
    }

    @Override
    public void borrar(int id) {
        // se deja vacio para la herencia abstracta
    }
}