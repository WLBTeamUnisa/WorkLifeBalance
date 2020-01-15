package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The persistent class for the PRENOTATION_DATE database table.
 * 
 */
@Entity
@Table(name = "prenotation_date")
@NamedQueries({ @NamedQuery(name = "PrenotationDate.findAll", query = "SELECT p FROM PrenotationDate p"),
        @NamedQuery(name = "PrenotationDate.findBySmartWorking", query = "SELECT p FROM PrenotationDate p WHERE p.id.employee = ?2 AND p.id.smartWorkingPrenotation = ?1") })
@Interceptors({ LoggerSingleton.class })
public class PrenotationDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PrenotationDatePK id;

    // bi-directional many-to-one association to SmartWorkingPrenotation
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "EMPLOYEE_EMAIL", columnDefinition = "varchar(37)", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "ID_PRENOTATION_SW", columnDefinition = "int(20)", nullable = false, updatable = false, insertable = false) })
    private SmartWorkingPrenotation smartWorkingPrenotation;

    public PrenotationDate() {
    }

    public PrenotationDatePK getId() {
        return this.id;
    }

    public void setId(PrenotationDatePK id) {
        this.id = id;
    }

    public SmartWorkingPrenotation getSmartWorkingPrenotation() {
        return this.smartWorkingPrenotation;
    }

    public void setSmartWorkingPrenotation(SmartWorkingPrenotation smartWorkingPrenotation) {
        this.smartWorkingPrenotation = smartWorkingPrenotation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrenotationDate other = (PrenotationDate) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (smartWorkingPrenotation == null) {
            if (other.smartWorkingPrenotation != null)
                return false;
        } else if (!smartWorkingPrenotation.equals(other.smartWorkingPrenotation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PrenotationDate [id=" + id + ", smartWorkingPrenotation=" + smartWorkingPrenotation + "]";
    }
}