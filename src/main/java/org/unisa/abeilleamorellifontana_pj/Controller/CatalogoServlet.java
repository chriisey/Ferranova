package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;
import org.unisa.abeilleamorellifontana_pj.Model.Promozione;
import org.unisa.abeilleamorellifontana_pj.Model.PromozioneDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO.*;

@WebServlet(name = "catalogo", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ArrayList<Prodotto> catalogo;
        if (request.getParameter("categoria") != null) {
            String categoria = request.getParameter("categoria");

            List<String> sottocategorie = null;


            if ((request.getParameter("ricerca") != null) || (request.getParameter("sottocategoria") != null) || (request.getParameter("prezzoMin") != null) || (request.getParameter("prezzoMax") != null)) {
                String ricerca = !request.getParameter("ricerca").equalsIgnoreCase("")
                        ? request.getParameter("ricerca")
                        : null;

                sottocategorie = (request.getParameterValues("sottocategorie") != null && request.getParameterValues("sottocategorie").length > 0)
                        ? List.of(request.getParameterValues("sottocategorie"))
                        : new ArrayList<>();
                BigDecimal prezzoMin = new BigDecimal(request.getParameter("prezzoMin")).equals(BigDecimal.ZERO)
                        ? null
                        : new BigDecimal(request.getParameter("prezzoMin"));
                BigDecimal prezzoMax = new BigDecimal(request.getParameter("prezzoMax")).equals(BigDecimal.valueOf(1000))
                        ? null
                        : new BigDecimal(request.getParameter("prezzoMax"));

                catalogo = (ArrayList<Prodotto>) doRetrieveByCriteria(ricerca, prezzoMin, prezzoMax, categoria, sottocategorie);

            } else
                catalogo = (ArrayList<Prodotto>) doRetrieveAllByMacrocategoria(categoria);
            request.setAttribute("catalogo", catalogo);

            ServletContext context = request.getServletContext();
            if (context.getAttribute("Categorie") != null) {
                HashMap<String, ArrayList<String>> categorie;
                categorie = (HashMap<String, ArrayList<String>>) context.getAttribute("Categorie");
                List<String> subCategorie = categorie.get(categoria);
                HashMap<String, Boolean> hashcategorie = new HashMap<>();
                for (String s : subCategorie) {
                    hashcategorie.put(s, (sottocategorie != null) ? sottocategorie.contains(s) : false);
                }
                request.setAttribute("subCategorie", hashcategorie);
            }

            ArrayList<Promozione> promozioni= (ArrayList<Promozione>) PromozioneDAO.doRetrieveAll();
            HashMap<Integer, Integer> promozioneHashMap = new HashMap<>();
            for (Promozione p : promozioni) {
                promozioneHashMap.put(p.getId(), p.getSconto());
            }
            request.setAttribute("promozioni", promozioneHashMap);
        }
        else {

            catalogo = (ArrayList<Prodotto>) doRetrieveAll();
            request.setAttribute("catalogo", catalogo);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
    }

}