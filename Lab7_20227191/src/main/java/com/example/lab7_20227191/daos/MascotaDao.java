package com.example.lab7_20227191.daos;

import com.example.lab7_20227191.beans.Dueno;
import com.example.lab7_20227191.beans.Especie;
import com.example.lab7_20227191.beans.Mascota;
import com.example.lab7_20227191.beans.Veterinario;
import java.sql.*;
import java.util.ArrayList;

public class MascotaDao extends DaoBase<Mascota> {

    // obtener todas las mascotas
    public ArrayList<Mascota> listar() {
        ArrayList<Mascota> lista = new ArrayList<>();
        String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, " +
                "e.idespecie, e.nombre AS especie_nombre, " +
                "v.idveterinario, v.nombre AS veterinario_nombre, v.especialidad, " +
                "d.iddueno, d.nombre AS dueno_nombre, d.telefono " +
                "FROM mascota m " +
                "INNER JOIN especie e ON m.especie_id = e.idespecie " +
                "INNER JOIN veterinario v ON m.veterinario_id = v.idveterinario " +
                "INNER JOIN dueno d ON m.dueno_id = d.iddueno ORDER BY m.idmascota DESC";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idmascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEdad(rs.getInt("edad"));
                m.setPeso(rs.getDouble("peso"));

                Especie esp = new Especie();
                esp.setIdEspecie(rs.getInt("idespecie"));
                esp.setNombre(rs.getString("especie_nombre"));
                m.setEspecie(esp);

                Veterinario vet = new Veterinario();
                vet.setIdVeterinario(rs.getInt("idveterinario"));
                vet.setNombre(rs.getString("veterinario_nombre"));
                vet.setEspecialidad(rs.getString("especialidad"));
                m.setVeterinario(vet);

                Dueno due = new Dueno();
                due.setIdDueno(rs.getInt("iddueno"));
                due.setNombre(rs.getString("dueno_nombre"));
                due.setTelefono(rs.getString("telefono"));
                m.setDueno(due);

                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // filtro por especie
    public ArrayList<Mascota> listarPorEspecie(int idEspecie) {
        ArrayList<Mascota> lista = new ArrayList<>();
        String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, " +
                "e.idespecie, e.nombre AS especie_nombre, " +
                "v.idveterinario, v.nombre AS veterinario_nombre, v.especialidad, " +
                "d.iddueno, d.nombre AS dueno_nombre, d.telefono " +
                "FROM mascota m " +
                "INNER JOIN especie e ON m.especie_id = e.idespecie " +
                "INNER JOIN veterinario v ON m.veterinario_id = v.idveterinario " +
                "INNER JOIN dueno d ON m.dueno_id = d.iddueno " +
                "WHERE m.especie_id = ? ORDER BY m.idmascota DESC";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEspecie);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Mascota m = new Mascota();
                    m.setIdMascota(rs.getInt("idmascota"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEdad(rs.getInt("edad"));
                    m.setPeso(rs.getDouble("peso"));

                    Especie esp = new Especie();
                    esp.setIdEspecie(rs.getInt("idespecie"));
                    esp.setNombre(rs.getString("especie_nombre"));
                    m.setEspecie(esp);

                    Veterinario vet = new Veterinario();
                    vet.setIdVeterinario(rs.getInt("idveterinario"));
                    vet.setNombre(rs.getString("veterinario_nombre"));
                    vet.setEspecialidad(rs.getString("especialidad"));
                    m.setVeterinario(vet);

                    Dueno due = new Dueno();
                    due.setIdDueno(rs.getInt("iddueno"));
                    due.setNombre(rs.getString("dueno_nombre"));
                    due.setTelefono(rs.getString("telefono"));
                    m.setDueno(due);

                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void crear(Mascota m) {
        String sql = "INSERT INTO mascota (nombre, edad, peso, especie_id, veterinario_id, dueno_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getNombre());
            pstmt.setInt(2, m.getEdad());
            pstmt.setDouble(3, m.getPeso());
            pstmt.setInt(4, m.getEspecie().getIdEspecie());

            // Logica delmanejo de nulos
            // Si la veterinaria tuviese la opcion opcional "sin veterinario", se valida de esta forma:
            if (m.getVeterinario().getIdVeterinario() == 0) {
                pstmt.setNull(5, Types.INTEGER);
            } else {
                pstmt.setInt(5, m.getVeterinario().getIdVeterinario());
            }

            pstmt.setInt(6, m.getDueno().getIdDueno());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sobreescritura obligatoria - Eliminar Mascota (Pregunta 3)
    @Override
    public void borrar(int id) {
        String sql = "DELETE FROM mascota WHERE idmascota = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}