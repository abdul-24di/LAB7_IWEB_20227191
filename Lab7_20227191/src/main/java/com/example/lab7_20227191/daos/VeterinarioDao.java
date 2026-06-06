package com.example.lab7_20227191.daos;

import com.example.lab7_20227191.beans.Veterinario;
import java.sql.*;
import java.util.ArrayList;

public class VeterinarioDao extends DaoBase<Veterinario> {

    public ArrayList<Veterinario> listar() {
        ArrayList<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setIdVeterinario(rs.getInt("idveterinario"));
                v.setNombre(rs.getString("nombre"));
                v.setEspecialidad(rs.getString("especialidad"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void crear(Veterinario object) {}

    @Override
    public void borrar(int id) {}
}