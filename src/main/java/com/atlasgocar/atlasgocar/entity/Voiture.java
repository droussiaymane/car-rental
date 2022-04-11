package com.atlasgocar.atlasgocar.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voiture")
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String immatriculation;

    @Column
    private String marque;

    @Column
    private String kilometrage;

    @Column
    private Long prixParJour;

    @Column
    private String type;

    @OneToMany(mappedBy = "voiture",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Long getPrixParJour() {
        return prixParJour;
    }

    public void setPrixParJour(Long prixParJour) {
        this.prixParJour = prixParJour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
