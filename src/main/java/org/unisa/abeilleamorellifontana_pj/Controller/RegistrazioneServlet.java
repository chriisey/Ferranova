package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;
import java.util.regex.Pattern;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.*;

@WebServlet(name = "registrazione", value = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    private static final String NAME_REGEX = "^[A-Za-z\\s]+$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
    private static final String PHONE_REGEX = "^\\d+$";
    private static final String ADDRESS_REGEX = "^[A-Za-z0-9\\s,.-]+$";

    public void init() {
        // Init code if needed
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String indirizzo = request.getParameter("indirizzo");

        UtenteDAO utenteDAO = new UtenteDAO();

        // Validazione dei campi
        if (!Pattern.matches(NAME_REGEX, nome) || !Pattern.matches(NAME_REGEX, cognome)) {
            request.setAttribute("error", "Nome o cognome non validi.");
            forwardToRegistrazione(request, response);
            return;
        }

        if (!Pattern.matches(EMAIL_REGEX, email)) {
            request.setAttribute("error", "Email non valida.");
            forwardToRegistrazione(request, response);
            return;
        }

        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            request.setAttribute("error", "Password non valida. Deve contenere almeno un numero, una lettera maiuscola, una minuscola, un carattere speciale e avere una lunghezza tra 8 e 20 caratteri.");
            forwardToRegistrazione(request, response);
            return;
        }

        if (!Pattern.matches(PHONE_REGEX, telefono)) {
            request.setAttribute("error", "Telefono non valido.");
            forwardToRegistrazione(request, response);
            return;
        }

        if (!Pattern.matches(ADDRESS_REGEX, indirizzo)) {
            request.setAttribute("error", "Indirizzo non valido.");
            forwardToRegistrazione(request, response);
            return;
        }


        Utente utente = new Utente(0, nome, cognome, email, SHA1PasswordVerifier.sha1Hash(password), telefono, false, indirizzo);

        int id;
        if ((id = utenteDAO.doInsert(utente)) > 0) {
            utente.setId(id);
            session.setAttribute("UtenteConnesso", utente);
            Carrello carrello = (Carrello) session.getAttribute("Carrello");
            carrello.setIdUtente(utente.getId());
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("registrazione.jsp?error=1");
        }
    }

    private void forwardToRegistrazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
        // Cleanup code if necessary
    }
}
