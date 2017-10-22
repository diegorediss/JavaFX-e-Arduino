/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author diego
 */

@Entity
@Table(name = "custo")
@NamedQueries({
    @NamedQuery(name = "custo.findAll",                 
            query = "SELECT c FROM custo c")
    , @NamedQuery(name = "custo.findByIdCusto",         
            query = "SELECT c FROM custo c WHERE c.id_custo = :idCusto")
    , @NamedQuery(name = "custo.findByDataCusto",       
            query = "SELECT c FROM custo c WHERE c.data_custo = :dataCusto")
    , @NamedQuery(name = "custo.findByCustoMao",        
            query = "SELECT c FROM custo c WHERE c.custo_mao = :custoMao")
    , @NamedQuery(name = "custo.findByCustoKwh",        
            query = "SELECT c FROM custo c WHERE c.custo_kwh = :custoKwh")
    , @NamedQuery(name = "custo.findByCustoManchaoI",   
            query = "SELECT c FROM custo c WHERE c.custo_manchaoI = :custoManchaoI")
    , @NamedQuery(name = "custo.findByCustoManchaoII",  
            query = "SELECT c FROM custo c WHERE c.custo_manchaoII = :custo_manchaoII")
    , @NamedQuery(name = "custo.findByCustoBorrachaI",  
            query = "SELECT c FROM custo c WHERE c.custo_borrachaI = :custoManchaoI")
    , @NamedQuery(name = "custo.findByCustoBorrachaII", 
            query = "SELECT c FROM custo c WHERE c.custo_borrachaII = :custoBorrachaII")
    , @NamedQuery(name = "custo.findByCustoColaI",      
            query = "SELECT c FROM custo c WHERE c.custo_colaI = :custoColaI")
    , @NamedQuery(name = "custo.findByCustoColaII",     
            query = "SELECT c FROM custo c WHERE c.custo_colaII = :custoColaII")
    , @NamedQuery(name = "custo.findByCustoEscovaI",    
            query = "SELECT c FROM custo c WHERE c.custo_escovaI = :custoEscovaI")
    , @NamedQuery(name = "custo.findByCustoEscovaII",   
            query = "SELECT c FROM custo c WHERE c.custo_escovaII = :custoEscovaII")        
    , @NamedQuery(name = "custo.findByMargemI",         
            query = "SELECT c FROM custo c WHERE c.margemI = :margemI")        
    , @NamedQuery(name = "custo.findByMargemII",        
            query = "SELECT c FROM custo c WHERE c.margemII = :margemII")})

public class Custo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_custo")
    private Integer idCusto;
    
    @Column(name = "data_custo")
    @Temporal(TemporalType.DATE)
    private Date dataCusto;
    
    @Basic(optional = true)
    @Column(name = "custo_mao", length=50)
    private String custoMao;
    
    @Basic(optional = true)
    @Column(name = "custo_kwh", length=50)
    private String custoKwh;
    
    @Basic(optional = true)
    @Column(name = "custo_manchaoI", length=30)
    private String custoManchaoI;
    
    @Basic(optional = true)
    @Column(name = "custo_manchaoII", length=30)
    private String custoManchaoII;
        
    @Basic(optional = true)
    @Column(name = "custo_borrachaI", length=30)
    private String custoBorrachaI;
    
    @Basic(optional = true)
    @Column(name = "custo_borrachaII", length=30)
    private String custoBorrachaII;
    
    @Basic(optional = true)
    @Column(name = "custo_colaI", length=30)
    private String custoColaI;
    
    @Basic(optional = true)
    @Column(name = "custo_colaII", length=30)
    private String custoColaII;
    
    @Basic(optional = true)
    @Column(name = "custo_escovaI", length=30)
    private String custoEscovaI;
    
    @Basic(optional = true)
    @Column(name = "custo_escovaII", length=30)
    private String custoEscovaII;
    
    @Basic(optional = true)
    @Column(name = "margemI", length=30)
    private String margemI;
    
    @Basic(optional = true)
    @Column(name = "margemII", length=30)
    private String margemII;

    
    public Custo() {
    }

    public Integer getIdCusto() {
        return idCusto;
    }

    public void setIdCusto(Integer idCusto) {
        this.idCusto = idCusto;
    }

    public Date getDataCusto() {
        return dataCusto;
    }

    public void setDataCusto(Date dataCusto) {
        this.dataCusto = dataCusto;
    }

    public String getCustoMao() {
        return custoMao;
    }

    public void setCustoMao(String custoMao) {
        this.custoMao = custoMao;
    }

    public String getCustoKwh() {
        return custoKwh;
    }

    public void setCustoKwh(String custoKwh) {
        this.custoKwh = custoKwh;
    }

    public String getCustoManchaoI() {
        return custoManchaoI;
    }

    public void setCustoManchaoI(String custoManchaoI) {
        this.custoManchaoI = custoManchaoI;
    }

    public String getCustoManchaoII() {
        return custoManchaoII;
    }

    public void setCustoManchaoII(String custoManchaoII) {
        this.custoManchaoII = custoManchaoII;
    }

    public String getCustoBorrachaI() {
        return custoBorrachaI;
    }

    public void setCustoBorrachaI(String custoBorrachaI) {
        this.custoBorrachaI = custoBorrachaI;
    }

    public String getCustoBorrachaII() {
        return custoBorrachaII;
    }

    public void setCustoBorrachaII(String custoBorrachaII) {
        this.custoBorrachaII = custoBorrachaII;
    }

    public String getCustoColaI() {
        return custoColaI;
    }

    public void setCustoColaI(String custoColaI) {
        this.custoColaI = custoColaI;
    }

    public String getCustoColaII() {
        return custoColaII;
    }

    public void setCustoColaII(String custoColaII) {
        this.custoColaII = custoColaII;
    }

    public String getCustoEscovaI() {
        return custoEscovaI;
    }

    public void setCustoEscovaI(String custoEscovaI) {
        this.custoEscovaI = custoEscovaI;
    }

    public String getCustoEscovaII() {
        return custoEscovaII;
    }

    public void setCustoEscovaII(String custoEscovaII) {
        this.custoEscovaII = custoEscovaII;
    }

    public String getMargemI() {
        return margemI;
    }

    public void setMargemI(String margemI) {
        this.margemI = margemI;
    }

    public String getMargemII() {
        return margemII;
    }

    public void setMargemII(String margemII) {
        this.margemII = margemII;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idCusto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Custo other = (Custo) obj;
        if (!Objects.equals(this.idCusto, other.idCusto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Custo{" + "idCusto=" + idCusto + '}';
    }

    
    
}
  