package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrdineDAO {

    public static int inserisciOrdine(Ordine ordine) {
        int id = -1;
        ResultSet rs = null;
        String insertOrderSQL = "INSERT INTO Ordine (id_utente, stato_ordine, prezzo_totale, spese_spedizione, data_ordine, data_spedizione, data_consegna, tipo_pagamento, indirizzo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?)";
        String insertProdottoOrdineSQL = "INSERT INTO Ordine_Prodotto(id_ordine,id_prodotto,quantità,prezzo_finale) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, ordine.getIdUtente());
            pstmt.setString(2, ordine.getStatoOrdine().toString());
            pstmt.setBigDecimal(3, ordine.getPrezzoOrdine());
            pstmt.setBigDecimal(4, ordine.getPrezzoSpedizione());
            pstmt.setDate(5, java.sql.Date.valueOf(ordine.getDataOrdine()));
            pstmt.setDate(6, ordine.getDataSpedizione() != null ? java.sql.Date.valueOf(ordine.getDataSpedizione()) : null);
            pstmt.setDate(7, ordine.getDataConsegna() != null ? java.sql.Date.valueOf(ordine.getDataConsegna()) : null);
            pstmt.setString(8, ordine.getTipoPagamento());
            pstmt.setString(9, ordine.getIndirizzo());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {

                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertProdottoOrdineSQL)) {

            HashMap<Integer, OrdineProdotto> ordineProdottoHashMap = (HashMap<Integer, OrdineProdotto>) ordine.getProdottiQuantitaOrdine();
            for (Map.Entry<Integer, OrdineProdotto> entry : ordineProdottoHashMap.entrySet()) {

                pstmt.setInt(1, id);
                pstmt.setInt(2, entry.getKey());
                pstmt.setInt(3, entry.getValue().getQuantita());
                pstmt.setBigDecimal(4, entry.getValue().getPrezzoFinale());
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static ArrayList<Ordine> doRetrieveAll() {
        ArrayList<Ordine> ordini = new ArrayList<>();
        String retrieveAllOrdersSQL = "SELECT * FROM Ordine ORDER BY id DESC";
        String retrieveProductsByOrderSQL = "SELECT * FROM Ordine_Prodotto WHERE id_ordine = ?";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(retrieveAllOrdersSQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ordine ordine = new Ordine(rs.getInt("id"), rs.getInt("id_utente"), Ordine.StatoOrdine.valueOf(rs.getString("stato_ordine").toUpperCase()), rs.getBigDecimal("prezzo_totale"), rs.getBigDecimal("spese_spedizione"), rs.getDate("data_ordine").toLocalDate(), rs.getDate("data_spedizione") != null ? rs.getDate("data_spedizione").toLocalDate() : null, rs.getDate("data_consegna") != null ? rs.getDate("data_consegna").toLocalDate() : null, rs.getString("tipo_pagamento"), rs.getString("indirizzo"));

                ordine.setProdottiQuantitaOrdine(retrieveProductsByOrder(ordine.getIdOrdine()));
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }

    public static Ordine doRetrieveById(int idOrdine) {
        String retrieveOrderSQL = "SELECT * FROM Ordine WHERE id = ?";
        String retrieveProductsByOrderSQL = "SELECT * FROM Ordine_Prodotto WHERE id_ordine = ?";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(retrieveOrderSQL)) {

            pstmt.setInt(1, idOrdine);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Ordine ordine = new Ordine(
                            rs.getInt("id"),
                            rs.getInt("id_utente"),
                            Ordine.StatoOrdine.valueOf(rs.getString("stato_ordine").toUpperCase()),
                            rs.getBigDecimal("prezzo_totale"),
                            rs.getBigDecimal("spese_spedizione"),
                            rs.getDate("data_ordine").toLocalDate(),
                            rs.getDate("data_spedizione") != null ? rs.getDate("data_spedizione").toLocalDate() : null,
                            rs.getDate("data_consegna") != null ? rs.getDate("data_consegna").toLocalDate() : null,
                            rs.getString("tipo_pagamento"),
                            rs.getString("indirizzo")
                    );

                    ordine.setProdottiQuantitaOrdine(retrieveProductsByOrder(idOrdine));
                    return ordine;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Metodo per ottenere tutti gli ordini di un utente specifico
    public static ArrayList<Ordine> doRetrieveByUserId(int idUtente) {
        ArrayList<Ordine> ordini = new ArrayList<>();
        String retrieveOrdersByUserSQL = "SELECT * FROM Ordine WHERE id_utente = ? ORDER BY id DESC";  //messo order by id desc per visualizzare gli ordini per id ordine decrescente
        String retrieveProductsByOrderSQL = "SELECT * FROM Ordine_Prodotto WHERE id_ordine = ?";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(retrieveOrdersByUserSQL)) {

            pstmt.setInt(1, idUtente);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(Ordine.StatoOrdine.fromString(rs.getString("stato_ordine")) + rs.getString("stato_ordine"));
                    Ordine ordine = new Ordine(rs.getInt("id"), rs.getInt("id_utente"), Ordine.StatoOrdine.fromString(rs.getString("stato_ordine")), rs.getBigDecimal("prezzo_totale"), rs.getBigDecimal("spese_spedizione"), rs.getDate("data_ordine").toLocalDate(), rs.getDate("data_spedizione") != null ? rs.getDate("data_spedizione").toLocalDate() : null, rs.getDate("data_consegna") != null ? rs.getDate("data_consegna").toLocalDate() : null, (rs.getString("tipo_pagamento")), rs.getString("indirizzo"));

                    ordine.setProdottiQuantitaOrdine(retrieveProductsByOrder(ordine.getIdOrdine()));
                    ordini.add(ordine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }


    // Metodo helper per ottenere i prodotti di un ordine
    private static HashMap<Integer, OrdineProdotto> retrieveProductsByOrder(int idOrdine) throws SQLException {
        Connection conn = ConPool.getConnection();
        HashMap<Integer, OrdineProdotto> prodottiQuantitaOrdine = new HashMap<>();
        String retrieveProductsByOrderSQL = "SELECT * FROM Ordine_Prodotto WHERE id_ordine = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(retrieveProductsByOrderSQL)) {
            pstmt.setInt(1, idOrdine);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idProdotto = rs.getInt("id_prodotto");
                    OrdineProdotto ordineProdotto = new OrdineProdotto(rs.getInt("quantità"), rs.getBigDecimal("prezzo_finale"));
                    prodottiQuantitaOrdine.put(idProdotto, ordineProdotto);
                }
            }
        }
        return prodottiQuantitaOrdine;
    }

    public static void updateOrderStatus(int orderId, Ordine.StatoOrdine status) {
        String query = "UPDATE Ordine SET stato_ordine = ? WHERE id = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, status.getStato());
            statement.setInt(2, orderId);

            statement.executeUpdate();
            System.out.println("Ordine aggiornato con successo!");

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento dell'ordine: " + e.getMessage());
        }
    }


    public static void updateShipmentDate(int orderId, LocalDate shipmentDate) throws SQLException {
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Ordine SET data_spedizione = ? WHERE id = ?")) {

            statement.setDate(1, Date.valueOf(shipmentDate));
            statement.setInt(2, orderId);


            statement.executeUpdate();
            System.out.println("Ordine aggiornato con successo!");

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento dell'ordine: " + e.getMessage());
        }
    }

    public static void updateDeliveryDate(int orderId, LocalDate deliveryDate) throws SQLException {
        String query = "UPDATE Ordine SET data_consegna = ? WHERE id = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, Date.valueOf(deliveryDate));
            statement.setInt(2, orderId);

            statement.executeUpdate();
            System.out.println("Ordine aggiornato con successo!");

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento dell'ordine: " + e.getMessage());
        }
    }

    public void updateOrdine(Ordine ordine) throws SQLException {
        String query = "UPDATE Ordine SET idUtente = ?, statoOrdine = ?, prezzoOrdine = ?, prezzoSpedizione = ?, " +
                "dataOrdine = ?, dataSpedizione = ?, dataConsegna = ?, tipoPagamento = ?, indirizzo = ? " +
                "WHERE idOrdine = ?";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ordine.getIdUtente());
            preparedStatement.setString(2, ordine.getStatoOrdine().getStato());
            preparedStatement.setBigDecimal(3, ordine.getPrezzoOrdine());
            preparedStatement.setBigDecimal(4, ordine.getPrezzoSpedizione());
            preparedStatement.setObject(5, ordine.getDataOrdine()); // LocalDate viene gestito come Object
            preparedStatement.setObject(6, ordine.getDataSpedizione());
            preparedStatement.setObject(7, ordine.getDataConsegna());
            preparedStatement.setString(8, ordine.getTipoPagamento());
            preparedStatement.setString(9, ordine.getIndirizzo());
            preparedStatement.setInt(10, ordine.getIdOrdine());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("L'ordine con ID " + ordine.getIdOrdine() + " è stato aggiornato con successo.");
            } else {
                System.out.println("Nessun ordine trovato con ID " + ordine.getIdOrdine() + ".");
            }
        }
    }

}

