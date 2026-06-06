package com.example.lab7_20227191.servlets;

import com.example.lab7_20227191.beans.Dueno;
import com.example.lab7_20227191.beans.Especie;
import com.example.lab7_20227191.beans.Mascota;
import com.example.lab7_20227191.beans.Veterinario;
import com.example.lab7_20227191.daos.DuenoDao;
import com.example.lab7_20227191.daos.EspecieDao;
import com.example.lab7_20227191.daos.MascotaDao;
import com.example.lab7_20227191.daos.VeterinarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MascotaServlet", value = {"/inicio", "/MascotaServlet"})
public class MascotaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        MascotaDao mascotaDao = new MascotaDao();
        EspecieDao especieDao = new EspecieDao();
        VeterinarioDao veterinarioDao = new VeterinarioDao();
        DuenoDao duenoDao = new DuenoDao();

        switch (action) {
            case "lista":
                // logica de filtro
                String especieFiltroStr = request.getParameter("filtrarEspecieId");
                int especieFiltro = (especieFiltroStr == null || especieFiltroStr.isEmpty()) ? 0 : Integer.parseInt(especieFiltroStr);

                if (especieFiltro == 0) {
                    request.setAttribute("listaMascotas", mascotaDao.listar());
                } else {
                    request.setAttribute("listaMascotas", mascotaDao.listarPorEspecie(especieFiltro));
                }

                request.setAttribute("listaEspecies", especieDao.listar());
                request.setAttribute("especieSeleccionada", especieFiltro);
                request.getRequestDispatcher("mascota/lista.jsp").forward(request, response);
                break;

            case "new":
                // se llena el combox
                request.setAttribute("listaEspecies", especieDao.listar());
                request.setAttribute("listaVeterinarios", veterinarioDao.listar());
                request.setAttribute("listaDuenos", duenoDao.listar());
                request.getRequestDispatcher("mascota/form.jsp").forward(request, response);
                break;

            case "del":
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    mascotaDao.borrar(Integer.parseInt(idStr));
                }
                response.sendRedirect(request.getContextPath() + "/inicio");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/inicio");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        MascotaDao mascotaDao = new MascotaDao();

        if ("guardar".equals(action)) {
            Mascota m = new Mascota();
            m.setNombre(request.getParameter("nombre"));
            m.setEdad(Integer.parseInt(request.getParameter("edad")));
            m.setPeso(Double.parseDouble(request.getParameter("peso")));

            Especie esp = new Especie();
            esp.setIdEspecie(Integer.parseInt(request.getParameter("especieId")));
            m.setEspecie(esp);

            Veterinario vet = new Veterinario();
            String vetIdStr = request.getParameter("veterinarioId");
            // ejemplo de logica "sin-jefe" / "sin-veterinario" si fuera opcional (regla 3)
            if ("sin-veterinario".equals(vetIdStr)) {
                vet.setIdVeterinario(0);
            } else {
                vet.setIdVeterinario(Integer.parseInt(vetIdStr));
            }
            m.setVeterinario(vet);

            Dueno due = new Dueno();
            due.setIdDueno(Integer.parseInt(request.getParameter("duenoId")));
            m.setDueno(due);

            mascotaDao.crear(m);
            response.sendRedirect(request.getContextPath() + "/inicio");
        }
    }
}