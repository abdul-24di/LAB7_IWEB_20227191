package com.example.lab7_20227191.daos;

import com.example.lab7_20227191.beans.Dueno;
import java.sql.*;
import java.util.ArrayList;

public class DuenoDao extends DaoBase<Dueno> {

    public ArrayList<Dueno> listar() {
        ArrayList<Dueno> lista = new ArrayList<>();
        String sql = "SELECT * FROM dueno";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dueno d = new Dueno();
                d.setIdDueno(rs.getInt("iddueno"));
                d.setNombre(rs.getString("nombre"));
                d.setTelefono(rs.getString("telefono"));
                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void crear(Dueno object) {}

    @Override
    public void borrar(int id) {}
}