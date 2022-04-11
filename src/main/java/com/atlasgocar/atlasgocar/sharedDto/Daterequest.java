package com.atlasgocar.atlasgocar.sharedDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Daterequest {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
