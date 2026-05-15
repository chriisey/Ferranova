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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "listaOrdini" , urlPatterns = "/listaOrdini")
public class ListaOrdiniServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession(false);
        Utente utente = (Utente) session.getAttribute("UtenteConnesso");
        ArrayList<Ordine> ordini = OrdineDAO.doRetrieveByUserId(utente.getId());

        List<Prodotto> prodotti = ProdottoDAO.doRetrieveAll();
        Map<Integer, Prodotto> mappaProdotti = new HashMap<>();

        for (Prodotto prodotto : prodotti) {
            mappaProdotti.put(prodotto.getId(), prodotto);
        }

// Passa la mappa alla JSP
        request.setAttribute("mappaProdotti", mappaProdotti);
        request.setAttribute("Ordini", ordini);


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ordini.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {

    }
}
