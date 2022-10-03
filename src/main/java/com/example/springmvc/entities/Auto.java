package com.example.springmvc.entities;

import com.example.springmvc.validator.AnnoImmatricolazione;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "veicolo")
public class Auto implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{NotNull.Auto.targa.validation}")
    @Column(name = "targa", unique = true)
    private String targa;
    @NotNull(message = "{NotNull.Auto.costruttore.validation}")
    @Column(name = "costruttore")
    private String costruttore;
    @NotNull(message = "{NotNull.Auto.modello.validation}")
    @Column(name = "modello")
    private String modello;

    @Min(value = 1886, message = "{Min.Auto.annoImmatricolazione.validation}")//ufficialmente l'anno della prima auto
    @AnnoImmatricolazione//controlla che non si inserisca un anno successivo a quello attuale
    @Column(name = "anno_immatricolazione")
    private int annoImmatricolazione;
    @NotNull(message = "{NotNull.Auto.tipologia.validation}")
    @Column(name = "tipologia")
    private String tipologia;

    @OneToMany(mappedBy = "auto")
    private Set<Prenotazione> prenotazioni;

    public Auto() {
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getCostruttore() {
        return costruttore;
    }

    public void setCostruttore(String costruttore) {
        this.costruttore = costruttore;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(int annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
