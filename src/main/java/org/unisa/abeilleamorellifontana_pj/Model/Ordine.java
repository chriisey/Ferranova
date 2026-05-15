package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ordine {

    private int idOrdine;
    private int idUtente;
    private StatoOrdine statoOrdine;
    private BigDecimal prezzoOrdine = BigDecimal.ZERO;
    private BigDecimal prezzoSpedizione = BigDecimal.ZERO;
    private Map<Integer, OrdineProdotto> ordiniProdotti;
    private LocalDate dataOrdine;
    private LocalDate dataSpedizione;
    private LocalDate dataConsegna;
    private String tipoPagamento;
    private String indirizzo;

    public Ordine(int idOrdine, int id_utente, Map<Integer, OrdineProdotto> prodottiQuantitaOrdine, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione) {
        this.idOrdine = idOrdine;
        this.idUtente = id_utente;
        this.ordiniProdotti = prodottiQuantitaOrdine;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.dataOrdine = LocalDate.now();
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
    }

    public Ordine(int idOrdine, int idUtente, StatoOrdine statoOrdine, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione, LocalDate dataOrdine, LocalDate dataSpedizione, LocalDate dataConsegna, String tipoPagamento) {
        this.idOrdine = idOrdine;
        this.idUtente = idUtente;
        this.statoOrdine = statoOrdine;
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
        this.dataOrdine = dataOrdine;
        this.dataSpedizione = dataSpedizione;
        this.dataConsegna = dataConsegna;
        this.tipoPagamento = tipoPagamento;
    }

    public Ordine(int idOrdine, int idUtente, StatoOrdine statoOrdine, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione, LocalDate dataOrdine, LocalDate dataSpedizione, LocalDate dataConsegna, String tipoPagamento, String indirizzo) {
        this.idOrdine = idOrdine;
        this.idUtente = idUtente;
        this.statoOrdine = statoOrdine;
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
        this.dataOrdine = dataOrdine;
        this.dataSpedizione = dataSpedizione;
        this.dataConsegna = dataConsegna;
        this.tipoPagamento = tipoPagamento;
        this.indirizzo = indirizzo;
    }
    public Ordine(int id_utente, Map<Integer, OrdineProdotto> prodottiQuantitaOrdine, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione) {
        this.idUtente = id_utente;
        this.ordiniProdotti = prodottiQuantitaOrdine;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.dataOrdine = LocalDate.now();
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
    }

    public Ordine(int idUtente, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione) {
        this.idUtente = idUtente;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
        this.dataOrdine = LocalDate.now();
        this.dataSpedizione = null;
        this.dataConsegna = null;
        this.tipoPagamento = "carta di credito";
    }

    public Ordine(int idUtente, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione,String indirizzo) {
        this.idUtente = idUtente;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
        this.dataOrdine = LocalDate.now();
        this.dataSpedizione = null;
        this.dataConsegna = null;
        this.tipoPagamento = "carta di credito";
        this.indirizzo = indirizzo;
    }

    public Ordine(int idUtente) {
        this.idUtente = idUtente;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.dataOrdine = LocalDate.now();
        this.dataSpedizione = null;
        this.dataConsegna = null;
        this.tipoPagamento = "carta di credito";
    }

    public Ordine(int idUtente,String indirizzo) {
        this.idUtente = idUtente;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.dataOrdine = LocalDate.now();
        this.dataSpedizione = null;
        this.dataConsegna = null;
        this.tipoPagamento = "carta di credito";
        this.indirizzo = indirizzo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Map<Integer, OrdineProdotto> getProdottiQuantitaOrdine() {
        return ordiniProdotti;
    }

    public void setProdottiQuantitaOrdine(Map<Integer, OrdineProdotto> ordiniProdotti) {
        this.ordiniProdotti = ordiniProdotti;
    }

    public void setOrdiniProdotti(Map<Integer, OrdineProdotto> ordiniProdotti) {
        this.ordiniProdotti = ordiniProdotti;
    }

    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public LocalDate getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione(LocalDate dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public BigDecimal getPrezzoOrdine() {
        return prezzoOrdine;
    }

    public void setPrezzoOrdine(BigDecimal prezzoOrdine) {

        this.prezzoOrdine = prezzoOrdine;
        this.setPrezzoSpedizione();
    }

    public BigDecimal getPrezzoSpedizione() {
        return prezzoSpedizione;
    }

    public void setPrezzoSpedizione() {
        if (this.prezzoOrdine.compareTo(new BigDecimal("100")) <= 0)
            this.prezzoSpedizione = new BigDecimal("10");

    }

    public void addCarrello(Carrello carrello, List<Prodotto> prodottoList) {
        HashMap<Integer, Integer> prodottiQuantita = (HashMap<Integer, Integer>) carrello.getProdottiQuantita();

        if (this.ordiniProdotti == null)
            this.ordiniProdotti = new HashMap<>();
        for (Prodotto p : prodottoList) {
            OrdineProdotto ordineProdotto = new OrdineProdotto(prodottiQuantita.get(p.getId()), p.getPrezzo());
            this.prezzoOrdine = this.prezzoOrdine.add(p.getPrezzo().multiply(new BigDecimal(ordineProdotto.getQuantita())));
            this.ordiniProdotti.put(p.getId(), ordineProdotto);
        }
        this.setPrezzoSpedizione();
    }


    // Enum StatoOrdine definito all'interno della classe Ordine
    public enum StatoOrdine {
        CONSEGNATO("consegnato"),
        SPEDITO("spedito"),
        DISPERSO("disperso"),
        ORDINATO("ordinato");

        private final String stato;

        StatoOrdine(String stato) {
            this.stato = stato;
        }

        public String getStato() {
            return stato;
        }

        public static StatoOrdine fromString(String text) {
            for (StatoOrdine stato : StatoOrdine.values()) {
                if (stato.stato.equalsIgnoreCase(text)) {
                    return stato;
                }
            }
            throw new IllegalArgumentException("No enum constant for string: " + text);
        }
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
