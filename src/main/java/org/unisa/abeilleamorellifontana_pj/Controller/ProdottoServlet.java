package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "prod", value = "/prod")
public class ProdottoServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address = "WEB-INF/prodotto.jsp";


        String id_prodotto = request.getParameter("id_prodotto");

        ArrayList<Promozione> promozioni= (ArrayList<Promozione>) PromozioneDAO.doRetrieveAll();
        HashMap<Integer, Integer> promozioneHashMap = new HashMap<>();
        for (Promozione p : promozioni) {
            promozioneHashMap.put(p.getId(), p.getSconto());
        }
        request.setAttribute("promozioni", promozioneHashMap);

        if (id_prodotto != null && Integer.parseInt(id_prodotto) > 0) {
            Prodotto input = ProdottoDAO.doRetrieveById(Integer.parseInt(id_prodotto));
            request.setAttribute("num_foto", PhotoManager.countPhotosInDirectory(input.getId(), getServletContext().getRealPath("/")));
            request.setAttribute("prodotto", input);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}