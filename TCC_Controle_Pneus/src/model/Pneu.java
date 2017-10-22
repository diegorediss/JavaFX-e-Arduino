/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */

@Entity
@Table(name = "pneu")
@NamedQueries({
    @NamedQuery(name = "pneu.findAll",              
            query = "SELECT p FROM pneu p")
    , @NamedQuery(name = "pneu.findByIdPneu",       
            query = "SELECT p FROM pneu p WHERE p.idpneu = :idpneu")
    , @NamedQuery(name = "pneu.findByDescricao",    
            query = "SELECT p FROM pneu p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "pneu.findByMedida",       
            query = "SELECT p FROM pneu p WHERE p.medida = :medida")    
    , @NamedQuery(name = "pneu.findByMarca",        
            query = "SELECT p FROM pneu p WHERE p.marca = :marca")    
    , @NamedQuery(name = "pneu.findBySerie",        
            query = "SELECT p FROM pneu p WHERE p.serie = :serie")    
    , @NamedQuery(name = "pneu.findByDot",          
            query = "SELECT p FROM pneu p WHERE p.dot = :dot")    
    , @NamedQuery(name = "pneu.findByObs",          
            query = "SELECT p FROM pneu p WHERE p.obs = :obs")})

public class Pneu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pneu")
    private Integer idPneu;
    
    
    @Column(name = "descricao")
    private String descricao;
    
    
    @Column(name = "medida")
    private String medida;
    
    
    @Column(name = "marca")
    private String marca;
        
    
    @Column(name = "serie")
    private String serie;
    
    //@NotNull
    @Column(name = "dot")
    private String dot;
        
    @Basic(optional = true)
    //@NotNull
    @Column(name = "obs")
    private String obs;
    
    @ManyToOne
    private Cliente cliente;
    
    @OneToMany(mappedBy="id_pneu")
    private List<Conserto> consertoList;
    
    
    
    public Pneu() {
    }
    

    public Integer getIdPneu() {
        return idPneu;
    }

    public void setIdPneu(Integer idPneu) {
        this.idPneu = idPneu;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Conserto> getConsertos() {
        return consertoList;
    }

    public void setConsertos(List<Conserto> consertos) {
        this.consertoList = consertoList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idPneu);
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
        final Pneu other = (Pneu) obj;
        if (!Objects.equals(this.idPneu, other.idPneu)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pneu{" + "idPneu=" + idPneu + '}';
    }



    
    
    
}