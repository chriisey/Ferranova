package org.unisa.abeilleamorellifontana_pj.Controller;

import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "CambioDatiUtenteServlet", value = "/CambioDatiUtenteServlet")
public class CambioDatiUtenteServlet extends HttpServlet {

    // Definizione delle regex come costanti
    private static final String NAME_REGEX = "^[A-Za-z\\s]+$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^\\d+$";
    private static final String ADDRESS_REGEX = "^[A-Za-z0-9\\s,.-]+$";

    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(ADDRESS_REGEX);

    public void init() {
    }

    @Override
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String indirizzo = request.getParameter("indirizzo");

        // Recupera l'utente connesso dalla sessione
        Utente utenteConnesso = (Utente) request.getSession().getAttribute("UtenteConnesso");

        // Validazione dei parametri
        if (!NAME_PATTERN.matcher(nome).matches()) {
            request.setAttribute("error", "Nome non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/profilo.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!NAME_PATTERN.matcher(cognome).matches()) {
            request.setAttribute("error", "Cognome non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/profilo.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            request.setAttribute("error", "Email non valida.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/profilo.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!PHONE_PATTERN.matcher(telefono).matches()) {
            request.setAttribute("error", "Numero di telefono non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/profilo.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (!ADDRESS_PATTERN.matcher(indirizzo).matches()) {
            request.setAttribute("error", "Indirizzo non valido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/profilo.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (utenteConnesso != null) {
            utenteConnesso.setNome(nome);
            utenteConnesso.setCognome(cognome);
            utenteConnesso.setEmail(email);
            utenteConnesso.setTelefono(telefono);
            utenteConnesso.setIndirizzo(indirizzo);

            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doUpdate(utenteConnesso);

            request.getSession().setAttribute("UtenteConnesso", utenteConnesso);
            response.sendRedirect(request.getContextPath() + "/profilo");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    public void destroy() {
    }
}
