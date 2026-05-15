package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;
import org.unisa.abeilleamorellifontana_pj.Model.PromozioneDAO;

import java.io.IOException;

@WebServlet(name = "rimuovi-promozione", urlPatterns = "/rimuovi-promozione")
public class RimuoviPromozioneDaProdotto extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String prod_id = request.getParameter("idProdotto");

            int prod_id1 = Integer.parseInt(prod_id);
            ProdottoDAO.aggiornaRecensioneNulla(prod_id1);

            request.getRequestDispatcher("admin").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}