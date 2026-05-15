package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "select-catagory", urlPatterns = "/select-category")
public class SelectCategoryServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ricerca = !request.getParameter("ricerca").equalsIgnoreCase("") ? request.getParameter("ricerca") : null;

        String categoriaParam = (request.getParameter("categoria") != null) && request.getParameter("categoria").equalsIgnoreCase("all") ? null : request.getParameter("categoria");


        String category = null;
        String subcategory = null;

        if ((categoriaParam != null) && !categoriaParam.equalsIgnoreCase("all")) {
            ServletContext context = getServletContext();
            Map<String, ArrayList<String>> categorie = (Map<String, ArrayList<String>>) context.getAttribute("Categorie");


            for (Map.Entry<String, ArrayList<String>> entry : categorie.entrySet()) {
                for (String subCat : entry.getValue()) {
                    if (categoriaParam.equalsIgnoreCase(entry.getKey() + "-" + subCat)) {
                        category = entry.getKey();
                        subcategory = subCat;
                        break;
                    }
                }
            }

        }

        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveByCriteria(ricerca, null, null, category, subcategory);
        request.setAttribute("prodotti", prodotti);

        request.getRequestDispatcher("admin").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {

    }


}