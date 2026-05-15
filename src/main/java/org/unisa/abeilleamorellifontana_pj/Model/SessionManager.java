package org.unisa.abeilleamorellifontana_pj.Model;


public class SessionManager {
    private Utente utente;
    private Carrello carrello;

    public SessionManager(Utente utente, Carrello carrello) {
        this.utente = utente;
        this.carrello = carrello;
    }

    public SessionManager() {
    }

    public SessionManager(Carrello carrello) {
        this.carrello = carrello;
    }

    public SessionManager(Utente utente) {
        this.utente = utente;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    private Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public boolean isLoggato() {

        return this.utente != null;
    }

    public boolean isAdmin() {

        return isLoggato() && this.utente.isAdmin();
    }


}
