package org.unisa.abeilleamorellifontana_pj.Controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;

import java.io.IOException;

@WebServlet(name = "carrelloAjax", value = "/carrelloAjax")
public class CarrelloAjaxServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession(false);
        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        String resp = request.getParameter("quantity");
        int quantity = Integer.parseInt(resp);

        String prod_id = request.getParameter("prod");
        int prod_id_d = Integer.parseInt(prod_id);

        Prodotto prodotto = ProdottoDAO.doRetrieveById(prod_id_d);
        int disponibilita = prodotto.getQuantita();
        int attuale = 0;

        if(carrello.getProdottiQuantita().containsKey(prod_id_d)){
            attuale = carrello.getProdottiQuantita().get(prod_id_d);
        }


        if (attuale + quantity > disponibilita) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            carrello.aggiungiProdotto(prod_id_d, quantity);
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int productId = Integer.parseInt(request.getParameter("prod"));
        String action = request.getParameter("action");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        Prodotto prodotto = ProdottoDAO.doRetrieveById(productId);
        int disponibilita = prodotto.getQuantita();
        int attuale = carrello.getProdottiQuantita().get(productId);

        if (action.equals("update")) {
            if(attuale + quantity > disponibilita) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantità richiesta superiore alla disponibilità");
                return;
            }else if(attuale + quantity > 0) {
                carrello.aggiungiProdotto(productId, quantity);
                int newQuantity = carrello.getProdottiQuantita().get(productId);
                response.getWriter().write(String.valueOf(newQuantity));
                response.setStatus(HttpServletResponse.SC_OK);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

        } else if (action.equals("remove")) {
            int newQuantity = carrello.getProdottiQuantita().get(productId);
            response.getWriter().write(String.valueOf(newQuantity));
            carrello.rimuoviProdotto(productId);
            response.setStatus(HttpServletResponse.SC_OK);

            if (carrello.getProdottiQuantita().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    public void destroy() {
    }

}
