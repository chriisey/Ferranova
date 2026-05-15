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
import java.util.regex.Pattern;

@WebServlet(name = "ordineServlet", value = "/ordineServlet")
public class OrdineServlet extends HttpServlet {

    // Definizione delle regex come costanti
    private static final Pattern NOME_COGNOME_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");
    private static final Pattern INDIRIZZO_PATTERN = Pattern.compile("^[A-Za-z0-9\\s,.-]+$");
    private static final Pattern INTESTATARIO_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");
    private static final Pattern NUMERO_CARTA_PATTERN = Pattern.compile("^\\d{16}$");
    private static final Pattern SCADENZA_PATTERN = Pattern.compile("^(0[1-9]|1[0-2])\\/([0-9]{4}|[0-9]{2})$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^\\d{3,4}$");

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("Carrello") == null) {
            response.sendRedirect("carrello.jsp");
            return;
        }

        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        if (carrello.getProdottiQuantita().isEmpty()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Riprende il prezzo totale
        ArrayList<Prodotto> lista = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
            lista.add(ProdottoDAO.doRetrieveById(entry.getKey()));
        }

        ArrayList<Promozione> promozioni = (ArrayList<Promozione>) PromozioneDAO.doRetrieveAll();
        HashMap<Integer, Integer> promozioneHashMap = new HashMap<>();
        for (Promozione p : promozioni) {
            promozioneHashMap.put(p.getId(), p.getSconto());
        }

        // Aggiorna le quantità dei prodotti nel database
        for (Prodotto p : lista) {
            int quantitaAcquistata = carrello.getProdottiQuantita().get(p.getId());
            int nuovaQuantita = p.getQuantita() - quantitaAcquistata;
            ProdottoDAO.aggiornaQuantitaProdotto(p.getId(), nuovaQuantita);
            BigDecimal b = p.getPrezzo();
            BigDecimal sconto = BigDecimal.ZERO;
            if (p.getIdPromozione() > 0 && promozioneHashMap.get(p.getIdPromozione()) != null) {
                sconto = new BigDecimal(promozioneHashMap.get(p.getIdPromozione())).divide(BigDecimal.valueOf(100));
            }
            b = b.multiply(BigDecimal.ONE.subtract(sconto));
            b = b.setScale(2, RoundingMode.HALF_EVEN);
            p.setPrezzo(b);
        }

        Utente u = (Utente) session.getAttribute("UtenteConnesso");

        // Recupera i parametri dalla richiesta
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String indirizzo = request.getParameter("indirizzo");
        String intestatario = request.getParameter("intestatario");
        String numeroCarta = request.getParameter("n_carta");
        String scadenza = request.getParameter("scadenza");
        String cvv = request.getParameter("cvv");

        // Valida i parametri uno per uno
        if (!NOME_COGNOME_PATTERN.matcher(nome).matches()) {
            request.setAttribute("error", "Nome non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!NOME_COGNOME_PATTERN.matcher(cognome).matches()) {
            request.setAttribute("error", "Cognome non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!INDIRIZZO_PATTERN.matcher(indirizzo).matches()) {
            request.setAttribute("error", "Indirizzo non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!INTESTATARIO_PATTERN.matcher(intestatario).matches()) {
            request.setAttribute("error", "Intestatario della carta non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!NUMERO_CARTA_PATTERN.matcher(numeroCarta).matches()) {
            request.setAttribute("error", "Numero della carta non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!SCADENZA_PATTERN.matcher(scadenza).matches()) {
            request.setAttribute("error", "Scadenza non valida.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!CVV_PATTERN.matcher(cvv).matches()) {
            request.setAttribute("error", "CVV non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/acquisto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Crea un nuovo ordine e lo inserisce nel database
        Ordine ordine = new Ordine(u.getId(), indirizzo);
        ordine.addCarrello(carrello, lista);
        OrdineDAO.inserisciOrdine(ordine);

        CarrelloDAO.doDelete(u.getId());
        session.removeAttribute("Carrello");

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AcquistoConSuccesso.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {

    }
}
