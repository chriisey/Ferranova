package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;

public class OrdineProdotto {
    private int quantita;
    private BigDecimal prezzoFinale;

    public OrdineProdotto(int quantita, BigDecimal prezzoFinale) {

        this.quantita = quantita;
        this.prezzoFinale = prezzoFinale;
    }


    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public BigDecimal getPrezzoFinale() {
        return prezzoFinale;
    }

    public void setPrezzoFinale(BigDecimal prezzoFinale) {
        this.prezzoFinale = prezzoFinale;
    }
}
