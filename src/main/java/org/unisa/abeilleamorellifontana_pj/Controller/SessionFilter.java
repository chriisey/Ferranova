package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;
import org.unisa.abeilleamorellifontana_pj.Model.SessionManager;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;

import java.io.IOException;


public class SessionFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        HttpSession s = req.getSession();
        if ((s.getAttribute("Carrello")) == null)
            s.setAttribute("Carrello", new Carrello());

        Utente u = (Utente) s.getAttribute("UtenteConnesso");

        chain.doFilter(req, res);
    }
}
