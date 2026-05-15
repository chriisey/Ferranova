package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/adminAjax")
public class AdminAjaxServlet extends HttpServlet {

    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodId = Integer.parseInt(request.getParameter("prod"));
        String action = request.getParameter("action");

        String jsonResponse = "";
        int newQuantity = 0;


        try {
            if ("update".equals(action)) {
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                newQuantity = updateProductQuantity(prodId, quantity);
                jsonResponse = "{ \"success\": true, \"newQuantity\": " + newQuantity + " }";
            } else if ("updateIdPromo".equals(action)) {
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                newQuantity = updateIdPromoProduct(prodId, quantity);
                jsonResponse = "{ \"success\": true, \"newQuantity\": " + newQuantity + " }";
            } else if ("azzera".equals(action)) {
                newQuantity = azzeraProductQuantity(prodId);
                jsonResponse = "{ \"success\": true, \"newQuantity\": " + newQuantity + " }";
            } else if ("updatePrice".equals(action)) {
                BigDecimal newPrice = BigDecimal.ZERO;
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                if(price.compareTo(BigDecimal.ZERO)>0){
                    newPrice = updateProductPrice(prodId, price);
                    jsonResponse = "{ \"success\": true, \"newPrice\": " + newPrice + " }";
                }else{
                    jsonResponse = "{ \"success\": false, \"newPrice\": " + newPrice + " }";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{ \"success\": false }";
        }

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.write(jsonResponse);
        }
    }

    private synchronized int updateProductQuantity(int prodId, int quantityChange) {
        try {
            int currentQuantity = ProdottoDAO.doRetrieveById(prodId).getQuantita();
            int newQuantity = currentQuantity + quantityChange;
            if (newQuantity < 0) {
                throw new IllegalArgumentException("La quantità del prodotto non può essere negativa");
            }
            ProdottoDAO.aggiornaQuantitaProdotto(prodId, newQuantity);
            return newQuantity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'aggiornamento della quantità del prodotto", e);
        }
    }

    private synchronized int azzeraProductQuantity(int prodId) {
        try {
            int newQuantity = 0;
            ProdottoDAO.aggiornaQuantitaProdotto(prodId, newQuantity);
            return newQuantity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'azzeramento della quantità del prodotto", e);
        }
    }

    private synchronized BigDecimal updateProductPrice(int prodId, BigDecimal newPrice) {
        try {
            ProdottoDAO.aggiornaPrezzoProdotto(prodId, newPrice);
            return newPrice;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'aggiornamento del prezzo del prodotto", e);
        }
    }

    private synchronized int updateIdPromoProduct(int prodId, int quantityChange) {
        try {
            int currentIdPromo = ProdottoDAO.doRetrieveById(prodId).getIdPromozione();
            int newIdPromo = currentIdPromo + quantityChange;
            if (newIdPromo <= 0) {
                throw new IllegalArgumentException("L'id del prodotto non può <=0");
            }
            ProdottoDAO.aggiornaIdPromoProdotto(prodId, newIdPromo);
            return newIdPromo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'aggiornamento l'id Promo del prodotto", e);
        }
    }
}
