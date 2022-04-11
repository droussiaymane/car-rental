package com.atlasgocar.atlasgocar.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long montantTotal;

    @Column
    private Date dateDePaiement;

    @Column
    private String mode;

    @Column
    private String status;

    @Column
    private Long montantDejaPaye;

    @Column
    private Long reste;





    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Long montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Date getDateDePaiement() {
        return dateDePaiement;
    }

    public void setDateDePaiement(Date dateDePaiement) {
        this.dateDePaiement = dateDePaiement;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMontantDejaPaye() {
        return montantDejaPaye;
    }

    public void setMontantDejaPaye(Long montantDejaPaye) {
        this.montantDejaPaye = montantDejaPaye;
    }

    public Long getReste() {
        return reste;
    }

    public void setReste(Long reste) {
        this.reste = reste;
    }


}
