/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "conserto")
@NamedQueries({
    @NamedQuery(name = "conserto.findAll",                          
            query = "SELECT c FROM conserto c")
    , @NamedQuery(name = "conserto.findByIdconserto",               
            query = "SELECT c FROM conserto c WHERE c.id_conserto = :idConserto")
    , @NamedQuery(name = "conserto.findByDataConserto",             
            query = "SELECT c FROM conserto c WHERE c.data_conserto = :dataConserto")
    , @NamedQuery(name = "conserto.findByTempoPreparo",             
            query = "SELECT c FROM conserto c WHERE c.tempo_preparo = :tempoPreparo")
    , @NamedQuery(name = "conserto.findByTempoVulcanizacao",        
            query = "SELECT c FROM conserto c WHERE c.tempo_vulcanizacao = :tempoVulcanizacao")
    , @NamedQuery(name = "conserto.findByTemperaturaVulcanizacao",  
            query = "SELECT c FROM conserto c WHERE c.temperatura_vulcanizacao = :temperaturaVulcanizacao")     
    , @NamedQuery(name = "conserto.findByResponsavel",              
            query = "SELECT c FROM conserto c WHERE c.responsavel = :responsavel")
    , @NamedQuery(name = "conserto.findByTipoConserto",             
            query = "SELECT c FROM conserto c WHERE c.tipo_conserto = :tipoConserto")
    , @NamedQuery(name = "conserto.findByCustoTotal",               
            query = "SELECT c FROM conserto c WHERE c.custo_total = :custoTotal")})

public class Conserto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id_conserto")
    private Integer idConserto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_conserto")
    private Date dataConserto;
    
    @Column(name = "tempo_preparo")
    private String tempoPreparo;
    
    @Column(name = "tempo_vulcanizacao")
    private String tempoVulcanizacao;
    
    @Column(name = "temperatura_vulcanizacao")
    private String temperaturaVulcanizacao;
    
    //@Column(name = "responsavel")
    //private String responsavel;
    
    @Column(name = "tipo_conserto")
    private String tipoConserto;
        
    @Column(name = "custo_total")
    private String custoTotal;
    
    @ManyToOne
    private Pneu pneu;
    
    @ManyToOne
    private Usuario responsavel;
    
    
    public Conserto() {
    }

    public Integer getIdConserto() {
        return idConserto;
    }

    public void setIdConserto(Integer idConserto) {
        this.idConserto = idConserto;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public String getTempoVulcanizacao() {
        return tempoVulcanizacao;
    }

    public void setTempoVulcanizacao(String tempoVulcanizacao) {
        this.tempoVulcanizacao = tempoVulcanizacao;
    }

    public String getTemperaturaVulcanizacao() {
        return temperaturaVulcanizacao;
    }

    public void setTemperaturaVulcanizacao(String temperaturaVulcanizacao) {
        this.temperaturaVulcanizacao = temperaturaVulcanizacao;
    }

    public String getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(String custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Pneu getPneu() {
        return pneu;
    }

    public void setPneu(Pneu pneu) {
        this.pneu = pneu;
    }

    public Usuario getUsuario() {
        return responsavel;
    }

    public void setUsuario(Usuario usuario) {
        this.responsavel = usuario;
    }

    public Date getDataConserto() {
        return dataConserto;
    }

    public void setDataConserto(Date dataConserto) {
        this.dataConserto = dataConserto;
    }

    public String getTipoConserto() {
        return tipoConserto;
    }

    public void setTipoConserto(String tipoConserto) {
        this.tipoConserto = tipoConserto;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idConserto);
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
        final Conserto other = (Conserto) obj;
        if (!Objects.equals(this.idConserto, other.idConserto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conserto{" + "idConserto=" + idConserto + '}';
    }
    

 
    
}