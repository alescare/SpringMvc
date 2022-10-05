package com.example.springmvc.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "utente")
public class Utente implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{NotNull.Utente.nome.validation}")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "{NotNull.Utente.cognome.validation}")
    @Column(name = "cognome")
    private String cognome;

    @NotNull(message = "{NotNull.Utente.dataNascita.validation}")
    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @NotNull(message = "{NotNull.Utente.username.validation}")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(message = "{NotNull.Utente.password.validation}")
    @Column(name = "password")
    private String password;

    @Column(name = "admin")
    private boolean admin;

    @Email(message = "{Email.Utente.email.validation}")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
    private Set<Prenotazione> prenotazioni;

    public Utente() {

    }

    public Utente(String nome, String cognome, LocalDate dataNascita, String username, String password, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id.equals(id);
    }
}
