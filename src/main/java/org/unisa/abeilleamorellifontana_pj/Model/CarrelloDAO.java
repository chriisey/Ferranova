package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CarrelloDAO {

    public static Carrello doRetrieve(int idUtente) {
        Carrello carrello = new Carrello(idUtente);
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Carrello WHERE id_utente = ?");
            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProdotto = rs.getInt("id_prodotto");
                int quantita = rs.getInt("quantità");
                carrello.aggiungiProdotto(idProdotto, quantita);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carrello;
    }

    public static void doDelete(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Carrello WHERE id_utente = ?");
            ps.setInt(1, idUtente);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doInsert(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
                int idProdotto = entry.getKey();
                int quantita = entry.getValue();
                PreparedStatement ps = con.prepareStatement("INSERT INTO Carrello (id_utente, id_prodotto, quantità) VALUES (?, ?, ?)");
                ps.setInt(1, carrello.getIdUtente());
                ps.setInt(2, idProdotto);
                ps.setInt(3, quantita);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
