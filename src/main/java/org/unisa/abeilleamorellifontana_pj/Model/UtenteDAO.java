package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.*;

import static org.unisa.abeilleamorellifontana_pj.Model.SHA1PasswordVerifier.sha1Hash;

public class UtenteDAO {

    public Utente doRetrieveByEmail(String mail) {
        Utente utente = null;


        String query = "SELECT * FROM Utente WHERE email = ?";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, mail);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String email = resultSet.getString("email");
                    String passwordhash = resultSet.getString("passwordhash");
                    String telefono = resultSet.getString("telefono");
                    boolean isAdmin = resultSet.getBoolean("is_admin");
                    String indirizzo = resultSet.getString("indirizzo");


                    utente = new Utente(id, nome, cognome, email, passwordhash, telefono, isAdmin, indirizzo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utente;
    }

    public int doInsert(Utente utente) {
        String query = "INSERT INTO Utente (nome, cognome, email, passwordhash, telefono, is_admin, indirizzo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, utente.getNome());
            pstmt.setString(2, utente.getCognome());
            pstmt.setString(3, utente.getEmail());
            pstmt.setString(4, utente.getPasswordhash());// Cripta la password prima di inserirla con sha1Hash che abbiamo creato nella classe verifier ce la spostiamo
            //nel dao per comodita'
            pstmt.setString(5, utente.getTelefono());
            pstmt.setBoolean(6, utente.isAdmin());
            pstmt.setString(7, utente.getIndirizzo());
            pstmt.executeUpdate();
            System.out.println("Utente inserito con successo!");

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
        }
        return (doRetrieveByEmail(utente.getEmail())).getId();
    }

    public void doUpdate(Utente utente) {
        String query = "UPDATE Utente SET nome = ?, cognome = ?, email = ?, passwordhash = ?, telefono = ?, indirizzo = ? WHERE id = ?";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setString(3, utente.getEmail());
            preparedStatement.setString(4, utente.getPasswordhash());
            preparedStatement.setString(5, utente.getTelefono());
            preparedStatement.setString(6, utente.getIndirizzo());
            preparedStatement.setInt(7, utente.getId());

            preparedStatement.executeUpdate();
            System.out.println("Utente aggiornato con successo!");

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento dell'utente: " + e.getMessage());
        }
    }
}



