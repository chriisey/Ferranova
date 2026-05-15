package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "carrello", value = "/carrello")

public class CarrelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession(false); //creiamo una nuova sessione
        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        ArrayList<Prodotto> lista = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
            lista.add(ProdottoDAO.doRetrieveById(entry.getKey()));
        }

        ArrayList<Promozione> promozioni = (ArrayList<Promozione>) PromozioneDAO.doRetrieveAll();
        HashMap<Integer, Integer> promozioneHashMap = new HashMap<>();
        for (Promozione p : promozioni) {
            promozioneHashMap.put(p.getId(), p.getSconto());
        }
        request.setAttribute("promozioni", promozioneHashMap);


        request.setAttribute("lista", lista);
        BigDecimal sum = BigDecimal.ZERO;
        for (Prodotto p : lista) {
            BigDecimal b = p.getPrezzo();
            BigDecimal sconto = BigDecimal.ZERO;
            if( p.getIdPromozione()>0  && promozioneHashMap.get(p.getIdPromozione()) != null) {
                sconto  = new BigDecimal(promozioneHashMap.get(p.getIdPromozione())).divide(BigDecimal.valueOf(100));
            }
            b = b.multiply(BigDecimal.ONE.subtract(sconto));
            b = b.setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal quantita = BigDecimal.valueOf(carrello.getProdottiQuantita().get(p.getId()));
            sum = sum.add(b.multiply(quantita));
        }
        request.setAttribute("sum", sum);


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}