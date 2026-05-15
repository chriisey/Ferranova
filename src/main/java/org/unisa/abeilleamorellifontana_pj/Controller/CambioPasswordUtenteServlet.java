package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;
import org.unisa.abeilleamorellifontana_pj.Model.SHA1PasswordVerifier;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "passwordServlet", urlPatterns = "/passwordServlet")
public class CambioPasswordUtenteServlet extends HttpServlet {

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        Utente utenteConnesso = (Utente) request.getSession().getAttribute("UtenteConnesso");

        if (utenteConnesso != null) {
            // Verifica se la nuova password rispetta la regex
            if (!Pattern.matches(PASSWORD_REGEX, newPassword)) {
                response.sendRedirect(request.getContextPath() + "/password?error=2");
                return;
            }

            // Verifica se la password corrente è corretta
            if (SHA1PasswordVerifier.verifyPassword(currentPassword, utenteConnesso.getPasswordhash())) {
                utenteConnesso.setPasswordhash(SHA1PasswordVerifier.sha1Hash(newPassword));
                UtenteDAO utenteDAO = new UtenteDAO();
                utenteDAO.doUpdate(utenteConnesso);

                // Imposta un messaggio di successo nella sessione
                request.getSession().setAttribute("message", "Password cambiata con successo!");

                response.sendRedirect(request.getContextPath() + "/profilo");
            } else {
                response.sendRedirect(request.getContextPath() + "/password?error=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
