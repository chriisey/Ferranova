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

@WebServlet(name = "aggiungi-promozione", urlPatterns = "/aggiungi-promozione")
public class AggiungiPromozioneServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String sconto = request.getParameter("sconto");

        int scontos = Integer.parseInt(sconto);

        if(scontos < 0) {
            response.sendRedirect("aggiungiPromozione.jsp");
        }

        Promozione promozione = new Promozione(titolo,descrizione,scontos);

        PromozioneDAO.doInsert(promozione);

        request.getRequestDispatcher("admin").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
