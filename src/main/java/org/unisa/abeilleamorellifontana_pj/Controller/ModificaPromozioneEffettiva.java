package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "modifica-promozione-effettiva", urlPatterns = "/modifica-promozione-effettiva")
public class ModificaPromozioneEffettiva extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id_promozione = request.getParameter("id_prom");
        int id_prom = Integer.parseInt(id_promozione);

        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String sconto = request.getParameter("sconto");

        int scontos = Integer.parseInt(sconto);


        PromozioneDAO.doUpdateByFields(id_prom,titolo,descrizione,scontos);

        request.getRequestDispatcher("admin").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}