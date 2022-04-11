package com.atlasgocar.atlasgocar.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String source;

    @Column
    private String status;

    @Column
    private Date dateDebut;

    @Column
    private Date dateFin;

    @Column
    private String lieuDeLivraison;

    @Column
    private String lieuDeRestitution;

    @Column
    private String etatVoiture;


    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    @ManyToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @OneToOne(mappedBy = "reservation")
    private Paiement paiement;


    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieuDeLivraison() {
        return lieuDeLivraison;
    }

    public void setLieuDeLivraison(String lieuDeLivraison) {
        this.lieuDeLivraison = lieuDeLivraison;
    }

    public String getLieuDeRestitution() {
        return lieuDeRestitution;
    }

    public void setLieuDeRestitution(String lieuDeRestitution) {
        this.lieuDeRestitution = lieuDeRestitution;
    }

    public String getEtatVoiture() {
        return etatVoiture;
    }

    public void setEtatVoiture(String etatVoiture) {
        this.etatVoiture = etatVoiture;
    }
}
