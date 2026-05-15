package org.unisa.abeilleamorellifontana_pj.Model;

import java.util.ArrayList;
import java.util.List;

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String passwordhash;
    private String telefono;
    private boolean isAdmin;
    private String indirizzo;

    public Utente(int id, String nome, String cognome,String email, String passwordhash, String telefono, boolean isAdmin, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.passwordhash = passwordhash;
        this.telefono = telefono;
        this.isAdmin = isAdmin;
        this.indirizzo = indirizzo;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}

