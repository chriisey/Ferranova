package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;

import java.io.IOException;

public class LoggedFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession s = req.getSession();
        Utente u = (Utente) s.getAttribute("UtenteConnesso");
        if (u == null) {
            res.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(req, res);
    }
}
