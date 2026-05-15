package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromozioneDAO {

    public static Promozione doRetrieveById(int id) {
        Promozione promozione = null;
        String query = "SELECT * FROM Promozione WHERE id = ?";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String titolo = resultSet.getString("titolo");
                    String descrizione = resultSet.getString("descrizione");
                    int sconto = resultSet.getInt("sconto");
                    promozione = new Promozione(id, titolo, descrizione, sconto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero della promozione con id: " + id);
            e.printStackTrace();
        }

        return promozione;
    }

    public synchronized static boolean doInsert(Promozione promozione) {
        String query = "INSERT INTO Promozione (titolo, descrizione, sconto) VALUES (?, ?, ?)";
        boolean inserito = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, promozione.getTitolo());
            pstmt.setString(2, promozione.getDescrizione());
            pstmt.setInt(3, promozione.getSconto());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                inserito = true;
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        promozione.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Promozione inserita con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento della promozione: " + e.getMessage());
            e.printStackTrace();
        }

        return inserito;
    }

    public static List<Promozione> doRetrieveAll() {
        List<Promozione> promozioni = new ArrayList<>();
        String query = "SELECT * FROM Promozione";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titolo = resultSet.getString("titolo");
                String descrizione = resultSet.getString("descrizione");
                int sconto = resultSet.getInt("sconto");
                Promozione promozione = new Promozione(id, titolo, descrizione, sconto);
                promozioni.add(promozione);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero di tutte le promozioni: " + e.getMessage());
            e.printStackTrace();
        }

        return promozioni;
    }

    public synchronized static boolean doUpdate(Promozione promozione) {
        String query = "UPDATE Promozione SET titolo = ?, descrizione = ?, sconto = ? WHERE id = ?";
        boolean aggiornato = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, promozione.getTitolo());
            pstmt.setString(2, promozione.getDescrizione());
            pstmt.setInt(3, promozione.getSconto());
            pstmt.setInt(4, promozione.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                aggiornato = true;
                System.out.println("Promozione aggiornata con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento della promozione: " + e.getMessage());
            e.printStackTrace();
        }

        return aggiornato;
    }

    public synchronized static boolean doDelete(int id) {
        String query = "DELETE FROM Promozione WHERE id = ?";
        boolean cancellato = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                cancellato = true;
                System.out.println("Promozione cancellata con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la cancellazione della promozione con id: " + id);
            e.printStackTrace();
        }

        return cancellato;
    }

    public synchronized static boolean doUpdateByFields(int id, String titolo, String descrizione, int sconto) {
        String query = "UPDATE Promozione SET titolo = ?, descrizione = ?, sconto = ? WHERE id = ?";
        boolean aggiornato = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setInt(3, sconto);
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                aggiornato = true;
                System.out.println("Promozione aggiornata con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento della promozione con id: " + id);
            e.printStackTrace();
        }

        return aggiornato;
    }


}
