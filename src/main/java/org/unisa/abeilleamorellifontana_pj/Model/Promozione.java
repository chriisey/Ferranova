package org.unisa.abeilleamorellifontana_pj.Model;

public class Promozione {
    private int id;
    private String titolo;
    private String descrizione;
    private int sconto;

    public Promozione(int id, String titolo, String descrizione, int sconto) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.sconto = sconto;
    }

    public Promozione(String titolo, String descrizione, int sconto) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.sconto = sconto;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        if (sconto <= 100) {
            this.sconto = sconto;
        } else {
            throw new IllegalArgumentException("Il valore dello sconto deve essere inferiore o uguale a 100.");
        }
    }

}
