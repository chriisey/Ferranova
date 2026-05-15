package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "addProduct", urlPatterns = "/addProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 50,      // 50 MB
        maxRequestSize = 1024 * 1024 * 100)  // 100 MB
public class AdminAddproduct extends HttpServlet {
    private static final int MAX_PHOTO_COUNT = 10;

    protected  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("UtenteConnesso") != null) {
            Utente u = (Utente) req.getSession().getAttribute("UtenteConnesso");
            if (!u.isAdmin()) {
                resp.sendRedirect("index.jsp");
                return;
            }

            String titolo = req.getParameter("titolo");
            String descrizione = req.getParameter("descrizione");
            int quantita = req.getParameter("quantita") != null ? Integer.parseInt(req.getParameter("quantita")) : null;
            if(quantita < 0) {
                resp.sendRedirect("aggiungiProdotto.jsp");
            }
            int idPromozione = req.getParameter("id_promozione").isEmpty() ? -1 : Integer.parseInt(req.getParameter("id_promozione"));
            String categoria = req.getParameter("categoria");
            BigDecimal prezzo = new BigDecimal(req.getParameter("prezzo"));

            String[] categoriaParts = categoria.split("-");
            String macrocategoria = categoriaParts[0];
            String sottocategoria = categoriaParts[1];


            Prodotto prodotto = ProdottoDAO.doInsert(new Prodotto(0, titolo, descrizione, quantita, idPromozione, macrocategoria, sottocategoria, prezzo));

            List<Part> fileParts = req.getParts().stream()
                    .filter(part -> "immagini".equals(part.getName()) && part.getSize() > 0)
                    .collect(Collectors.toList());
            if (!PhotoManager.PhotosUpload(fileParts, prodotto.getId(), getServletContext().getRealPath(""))) {
                req.setAttribute("errorMessage", "Puoi caricare un massimo di " + MAX_PHOTO_COUNT + " foto per prodotto.");
                req.getRequestDispatcher("/aggiungiProdotto.jsp").forward(req, resp);
                return;
            }

            // Reindirizzare alla pagina di amministrazione o visualizzare un messaggio di successo
            resp.sendRedirect(getServletContext().getContextPath() + "/admin");
        }
    }

}
