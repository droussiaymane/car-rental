package com.atlasgocar.atlasgocar.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String telephone;
    @Column
    private String cin;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinCin;
    @Column

    private String passeport;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebutPasseport;
    @Column
    private String permis;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebutPermis;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDeNaissance;

    @Column
    private String hotel;

    @Column
    private String adresse;

    @Column
    private String chambre;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateFinCin() {
        return dateFinCin;
    }

    public void setDateFinCin(Date dateFinCin) {
        this.dateFinCin = dateFinCin;
    }

    public String getPasseport() {
        return passeport;
    }

    public void setPasseport(String passeport) {
        this.passeport = passeport;
    }

    public Date getDateDebutPasseport() {
        return dateDebutPasseport;
    }

    public void setDateDebutPasseport(Date dateDebutPasseport) {
        this.dateDebutPasseport = dateDebutPasseport;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public Date getDateDebutPermis() {
        return dateDebutPermis;
    }

    public void setDateDebutPermis(Date dateDebutPermis) {
        this.dateDebutPermis = dateDebutPermis;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getChambre() {
        return chambre;
    }

    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
