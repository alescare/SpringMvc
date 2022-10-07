package com.example.springmvc.entities;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
public class Prenotazione implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "data_inizio")
    @Past(message = "{Past.Prenotazione.dataInizio.validation}")
    private LocalDate dataInizio;
    @Column(name = "data_fine")
    @Past(message = "{Past.Prenotazione.dataFine.validation}")
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @Column(name = "approvata")
    private boolean approvata;



    @ManyToOne
    @JoinColumn(name = "auto", nullable = true)
    private Auto auto;

    public Prenotazione() {
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isApprovata() {
        return approvata;
    }

    public void setApprovata(boolean approvata) {
        this.approvata = approvata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

}
